package br.ucsal.voltapramim.config;

import br.ucsal.voltapramim.aplicacao.BuscarItens;
import br.ucsal.voltapramim.aplicacao.CadastrarItem;
import br.ucsal.voltapramim.aplicacao.ConsultarItem;
import br.ucsal.voltapramim.aplicacao.porta.ArmazenamentoDeFotos;
import br.ucsal.voltapramim.aplicacao.porta.RepositorioDeItens;
import br.ucsal.voltapramim.infraestrutura.busca.BuscaPorRelevancia;
import br.ucsal.voltapramim.infraestrutura.busca.BuscaRecentes;
import br.ucsal.voltapramim.infraestrutura.demo.DadosDemonstracao;
import br.ucsal.voltapramim.infraestrutura.eventos.AuditoriaObserver;
import br.ucsal.voltapramim.infraestrutura.eventos.EstatisticasObserver;
import br.ucsal.voltapramim.infraestrutura.eventos.PublicadorEmMemoria;
import br.ucsal.voltapramim.infraestrutura.fotos.ArmazenamentoLocalDeFotos;
import br.ucsal.voltapramim.infraestrutura.persistencia.ItemRepositorioEmMemoria;
import br.ucsal.voltapramim.web.ChaveRota;
import br.ucsal.voltapramim.web.ComandoWeb;
import br.ucsal.voltapramim.web.Roteador;
import br.ucsal.voltapramim.web.comandos.BuscaComando;
import br.ucsal.voltapramim.web.comandos.DetalheComando;
import br.ucsal.voltapramim.web.comandos.DesignSystemComando;
import br.ucsal.voltapramim.web.comandos.InicioComando;
import br.ucsal.voltapramim.web.comandos.NovoItemComando;
import br.ucsal.voltapramim.web.comandos.SalvarItemComando;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.nio.file.Path;
import java.util.Map;

public final class AppBootstrap implements ServletContextListener {
    public static final String ROTEADOR = "voltapramim.roteador";

    @Override
    public void contextInitialized(ServletContextEvent evento) {
        RepositorioDeItens repositorio =
                new ItemRepositorioEmMemoria();

        PublicadorEmMemoria publicador =
                new PublicadorEmMemoria();

        publicador.inscrever(new AuditoriaObserver());
        publicador.inscrever(new EstatisticasObserver());

        ArmazenamentoDeFotos fotos =
                new ArmazenamentoLocalDeFotos(diretorioFotos());

        CadastrarItem cadastrar =
                new CadastrarItem(repositorio, publicador, fotos);

        BuscarItens buscar = new BuscarItens(
                repositorio,
                new BuscaPorRelevancia(),
                new BuscaRecentes()
        );

        ConsultarItem consultar = new ConsultarItem(repositorio);

        DadosDemonstracao.carregar(cadastrar);

        Map<ChaveRota, ComandoWeb> comandos = Map.of(
                new ChaveRota("GET", "/"),
                new InicioComando(buscar),

                new ChaveRota("GET", "/home"),
                new InicioComando(buscar),

                new ChaveRota("GET", "/buscar"),
                new BuscaComando(buscar),

                new ChaveRota("GET", "/achei"),
                new NovoItemComando(),

                new ChaveRota("POST", "/achei"),
                new SalvarItemComando(cadastrar),

                new ChaveRota("GET", "/design-system"),
                new DesignSystemComando()
        );

        evento.getServletContext().setAttribute(
                ROTEADOR,
                new Roteador(
                        comandos,
                        new DetalheComando(consultar)
                )
        );
    }

    private Path diretorioFotos() {
        String diretorioConfigurado =
                System.getenv("VPM_FOTOS_DIR");

        if (diretorioConfigurado == null
                || diretorioConfigurado.isBlank()) {

            return Path.of(
                    System.getProperty("java.io.tmpdir"),
                    "voltapramim-fotos"
            );
        }

        return Path.of(diretorioConfigurado);
    }
}