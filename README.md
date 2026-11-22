Failed to create stream fd: Operation not permitted
Failed to create stream fd: Operation not permitted
Failed to create stream fd: Operation not permitted
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

Os quatro itens da página inicial são exemplos independentes dos registros usados pelas páginas existentes de cadastro e busca.

## Funcionalidades existentes

- Página inicial com itens recentes.
- Cadastro público com título, descrição, categoria e local.
- Busca sem diferenciar maiúsculas/minúsculas ou acentos; filtros de categoria e local podem ser combinados.
- Página de detalhes com referência pública e status.
- Ciclo de vida modelado com State. Os itens começam como “Aguardando entrega” e esse estado não promete retirada.
- Busca por relevância e por itens recentes usando Strategy.
- Eventos de cadastro com observers para auditoria e contagem.
- Despacho HTTP por comandos.

## Limites desta versão

O armazenamento é em memória. Upload de fotos, painel de operações, autenticação, CSRF nas ações administrativas e transições pela interface ainda não foram implementados. A referência pública serve apenas para localizar o registro e não prova propriedade. Este protótipo não representa um serviço oficial da UCSal.

Os testes automatizados cobrem regras de estado, cadastro, busca e filtros. O roteiro manual registra verificações complementares de produto que ainda precisam ser realizadas pela equipe.

O PDF de especificação está em [`docs/especificacao/VoltaPraMim_Project.pdf`](docs/especificacao/VoltaPraMim_Project.pdf). Consulte também a [arquitetura](docs/arquitetura.md), as [decisões pendentes](docs/decisoes.md) e o [roteiro de teste manual](tests/roteiro-teste-manual.md).
