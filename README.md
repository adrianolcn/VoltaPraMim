Failed to create stream fd: Operation not permitted
Failed to create stream fd: Operation not permitted
Failed to create stream fd: Operation not permitted
# VoltaPraMim

Página inicial do mural acadêmico de achados e perdidos da UCSal. A aplicação usa Java 17, Maven, Servlet/JSP e CSS/JavaScript puros. A aplicação carrega itens fictícios de demonstração em memória.

## Requisitos

- JDK 17
- Maven 3.9+

## Executar localmente

Na raiz do projeto:

```sh
mvn test
mvn jetty:run
```

## Fotos de objetos

O cadastro aceita foto opcional para itens que não sejam da categoria
`DOCUMENTOS`.

Formatos permitidos:

- JPEG;
- PNG;
- até 5 MiB.

A aplicação valida o conteúdo real do arquivo, não apenas sua extensão.
Arquivos enviados ficam fora de `src/main/webapp`, recebem um identificador
UUID e são acessados somente pela rota controlada `/fotos/{id}`.

Para escolher a pasta local de fotos no PowerShell:

```powershell
$env:VPM_FOTOS_DIR = "$env:USERPROFILE\voltapramim-fotos"
mvn jetty:run

Abra <http://localhost:8080>. O servidor continua rodando no terminal; use `Ctrl+C` para pará-lo. Os dados de demonstração são recriados a cada inicialização.

## Estrutura da página

- `AppBootstrap` conecta um único `ItemRepositorioEmMemoria` ao cadastro, à busca e aos detalhes; `DadosDemonstracao` carrega os exemplos nesse repositório.
- `FrontControllerServlet` e `Roteador` encaminham `/` e `/home` ao `InicioComando`, que consulta `BuscarItens` e renderiza `WEB-INF/views/index.jsp`. O cadastro e a busca usam a mesma fonte de dados.
- A busca instantânea em `static/js/home.js` filtra os itens ativos renderizados; enviar o formulário abre a busca completa em `/buscar`.
- Os dados ficam em memória e são apagados ao reiniciar. Não há banco de dados nem framework front-end.

A especificação revisada menciona Java 21; este repositório usa Java 17. A versão definitiva deve ser confirmada pela equipe.

## Funcionalidades existentes

- Página inicial com itens recentes.
- Cadastro público com título, descrição, categoria e local.
- Busca sem diferenciar maiúsculas/minúsculas ou acentos; filtros de categoria e local podem ser combinados.
- Página de detalhes com referência pública e status.
- Ciclo de vida modelado com State. Os itens começam como “Aguardando entrega” e esse estado não promete retirada.
- Busca por relevância e por itens recentes usando Strategy.
- Eventos de cadastro com observers para auditoria e contagem.
- Despacho HTTP por comandos.
- Upload opcional de imagens JPEG e PNG para itens permitidos.
- Bloqueio de foto para a categoria Documentos.
- Armazenamento local privado das fotos com UUID.

## Limites desta versão

O armazenamento é em memória e os itens são apagados ao reiniciar. As fotos
podem permanecer no diretório local configurado por `VPM_FOTOS_DIR`; a limpeza
desse diretório ainda não é automatizada. O painel de operações, autenticação,
CSRF nas ações administrativas e transições pela interface ainda não foram
implementados.

Os testes automatizados cobrem regras de estado, cadastro, busca e filtros. O roteiro manual registra verificações complementares de produto que ainda precisam ser realizadas pela equipe.

O PDF de especificação está em [`docs/especificacao/VoltaPraMim_Project.pdf`](docs/especificacao/VoltaPraMim_Project.pdf). Consulte também a [arquitetura](docs/arquitetura.md), as [decisões pendentes](docs/decisoes.md) e o [roteiro de teste manual](tests/roteiro-teste-manual.md).
