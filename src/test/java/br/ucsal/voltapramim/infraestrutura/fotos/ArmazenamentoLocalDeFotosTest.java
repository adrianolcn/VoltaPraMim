package br.ucsal.voltapramim.infraestrutura.fotos;

import br.ucsal.voltapramim.aplicacao.porta.FotoUpload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArmazenamentoLocalDeFotosTest {

    @TempDir
    Path diretorioTemporario;

    @Test
    void salvaELocalizaImagemPngValida() throws IOException {
        ArmazenamentoLocalDeFotos armazenamento =
                new ArmazenamentoLocalDeFotos(diretorioTemporario);

        byte[] png = criarImagem("png");

        String fotoId = armazenamento.salvar(
                new FotoUpload(png, "image/png")
        );

        var foto = armazenamento.buscarPorId(fotoId);

        assertTrue(foto.isPresent());
        assertEquals("image/png", foto.orElseThrow().contentType());
        assertEquals(png.length, foto.orElseThrow().conteudo().length);
    }

    @Test
    void salvaELocalizaImagemJpegValida() throws IOException {
        ArmazenamentoLocalDeFotos armazenamento =
                new ArmazenamentoLocalDeFotos(diretorioTemporario);

        byte[] jpeg = criarImagem("jpg");

        String fotoId = armazenamento.salvar(
                new FotoUpload(jpeg, "image/jpeg")
        );

        var foto = armazenamento.buscarPorId(fotoId);

        assertTrue(foto.isPresent());
        assertEquals("image/jpeg", foto.orElseThrow().contentType());
    }

    @Test
    void rejeitaArquivoQueNaoEImagem() {
        ArmazenamentoLocalDeFotos armazenamento =
                new ArmazenamentoLocalDeFotos(diretorioTemporario);

        FotoUpload texto = new FotoUpload(
                "isto não é uma foto".getBytes(),
                "image/jpeg"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> armazenamento.salvar(texto)
        );
    }

    @Test
    void rejeitaArquivoMaiorQueCincoMiB() {
        ArmazenamentoLocalDeFotos armazenamento =
                new ArmazenamentoLocalDeFotos(diretorioTemporario);

        byte[] conteudoExcessivo = new byte[5 * 1024 * 1024 + 1];

        FotoUpload foto = new FotoUpload(
                conteudoExcessivo,
                "image/png"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> armazenamento.salvar(foto)
        );
    }

    @Test
    void naoLocalizaIdentificadorInvalidoOuInexistente() {
        ArmazenamentoLocalDeFotos armazenamento =
                new ArmazenamentoLocalDeFotos(diretorioTemporario);

        assertFalse(armazenamento.buscarPorId("../segredo").isPresent());

        assertFalse(
                armazenamento.buscarPorId(
                        "5e2edca0-858f-4e4c-827c-29308063a86b"
                ).isPresent()
        );
    }

    private byte[] criarImagem(String formato) throws IOException {
        BufferedImage imagem = new BufferedImage(
                2,
                2,
                BufferedImage.TYPE_INT_RGB
        );

        try (ByteArrayOutputStream saida = new ByteArrayOutputStream()) {
            ImageIO.write(imagem, formato, saida);
            return saida.toByteArray();
        }
    }
}