package br.ucsal.voltapramim.infraestrutura.fotos;

import br.ucsal.voltapramim.aplicacao.porta.ArmazenamentoDeFotos;
import br.ucsal.voltapramim.aplicacao.porta.FotoArmazenada;
import br.ucsal.voltapramim.aplicacao.porta.FotoUpload;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.UUID;

public final class ArmazenamentoLocalDeFotos
        implements ArmazenamentoDeFotos {

    private static final int TAMANHO_MAXIMO = 5 * 1024 * 1024;

    private final Path diretorio;

    public ArmazenamentoLocalDeFotos(Path diretorio) {
        this.diretorio = diretorio.toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.diretorio);
        } catch (IOException erro) {
            throw new IllegalStateException(
                    "Não foi possível preparar o diretório de fotos.",
                    erro
            );
        }
    }

    @Override
    public String salvar(FotoUpload foto) {
        validar(foto);

        String fotoId = UUID.randomUUID().toString();
        Path destino = diretorio.resolve(fotoId + ".img").normalize();

        if (!destino.startsWith(diretorio)) {
            throw new IllegalArgumentException("Arquivo de foto inválido.");
        }

        try {
            Files.write(
                    destino,
                    foto.conteudo(),
                    StandardOpenOption.CREATE_NEW
            );

            return fotoId;
        } catch (IOException erro) {
            throw new IllegalStateException(
                    "Não foi possível salvar a foto.",
                    erro
            );
        }
    }

    @Override
    public void remover(String fotoId) {
        if (fotoId == null || !fotoId.matches("[0-9a-fA-F-]{36}")) throw new IllegalArgumentException("Identificador inválido.");
        try { Files.deleteIfExists(diretorio.resolve(fotoId + ".img")); }
        catch (IOException erro) { throw new IllegalStateException("Não foi possível remover a foto.", erro); }
    }

    @Override
    public Optional<FotoArmazenada> buscarPorId(String fotoId) {
        if (fotoId == null || !fotoId.matches("[0-9a-fA-F-]{36}")) {
            return Optional.empty();
        }

        Path arquivo = diretorio.resolve(fotoId + ".img").normalize();

        if (!arquivo.startsWith(diretorio) || !Files.isRegularFile(arquivo)) {
            return Optional.empty();
        }

        try {
            byte[] conteudo = Files.readAllBytes(arquivo);
            String contentType = detectarTipo(conteudo);

            return Optional.of(new FotoArmazenada(conteudo, contentType));
        } catch (IOException | IllegalArgumentException erro) {
            return Optional.empty();
        }
    }

    private void validar(FotoUpload foto) {
        if (foto == null || foto.conteudo().length == 0) {
            throw new IllegalArgumentException(
                    "Envie uma imagem JPEG ou PNG."
            );
        }

        if (foto.conteudo().length > TAMANHO_MAXIMO) {
            throw new IllegalArgumentException(
                    "A foto deve ter no máximo 5 MiB."
            );
        }

        detectarTipo(foto.conteudo());
    }

    private String detectarTipo(byte[] conteudo) {
        String tipo = ehPng(conteudo) ? "image/png" : ehJpeg(conteudo) ? "image/jpeg" : null;
        if (tipo == null) throw new IllegalArgumentException("Envie somente imagens JPEG ou PNG.");
        try (ImageInputStream entrada = ImageIO.createImageInputStream(new ByteArrayInputStream(conteudo))) {
            var leitores = ImageIO.getImageReaders(entrada);
            if (!leitores.hasNext()) throw new IllegalArgumentException("O arquivo enviado não é uma imagem válida.");
            ImageReader leitor = leitores.next();
            try {
                leitor.setInput(entrada, true, true);
                int largura = leitor.getWidth(0);
                int altura = leitor.getHeight(0);
                if (largura <= 0 || altura <= 0 || (long) largura * altura > 25_000_000L)
                    throw new IllegalArgumentException("A imagem excede o limite de dimensões.");
                if (leitor.read(0) == null) throw new IllegalArgumentException("O arquivo enviado não é uma imagem válida.");
                return tipo;
            } finally { leitor.dispose(); }
        } catch (IOException erro) {
            throw new IllegalArgumentException("Não foi possível validar a imagem enviada.");
        }
    }

    private boolean ehPng(byte[] bytes) {
        return bytes.length >= 8
                && bytes[0] == (byte) 0x89
                && bytes[1] == 0x50
                && bytes[2] == 0x4E
                && bytes[3] == 0x47
                && bytes[4] == 0x0D
                && bytes[5] == 0x0A
                && bytes[6] == 0x1A
                && bytes[7] == 0x0A;
    }

    private boolean ehJpeg(byte[] bytes) {
        return bytes.length >= 3
                && bytes[0] == (byte) 0xFF
                && bytes[1] == (byte) 0xD8
                && bytes[2] == (byte) 0xFF;
    }

}