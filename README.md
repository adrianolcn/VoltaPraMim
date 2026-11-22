# VoltaPraMim

Página inicial do mural acadêmico de achados e perdidos da UCSal. A aplicação usa Java 17, Maven, Servlet/JSP e CSS/JavaScript puros. Os quatro itens mostrados são dados de demonstração em memória.

## Requisitos

- JDK 17
- Maven 3.9+

## Executar localmente

Na raiz do projeto:

```sh
mvn test
mvn jetty:run
```

Abra <http://localhost:8080>. O servidor continua rodando no terminal; use `Ctrl+C` para pará-lo. Os dados de demonstração são recriados a cada inicialização.

## Estrutura da página

- `HomeServlet` carrega os itens e encaminha para `WEB-INF/views/index.jsp`.
- `ItemRepository` fornece quatro registros em memória.
- `static/css` contém tokens e estilos próprios; `static/js/home.js` faz a busca e os filtros instantâneos.
- Não há banco de dados nem framework front-end.
