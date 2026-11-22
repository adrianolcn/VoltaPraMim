# VoltaPraMim

Protótipo acadêmico de achados e perdidos para a comunidade da UCSal. Esta primeira versão permite cadastrar objetos encontrados, procurar itens ativos e consultar seus detalhes.

## Requisitos

- JDK 21
- Maven 3.9+

## Executar localmente

Na raiz do projeto:

```sh
mvn test
mvn jetty:run
```

Abra <http://localhost:8080>. A aplicação inicia com três registros fictícios para demonstração. Os dados novos ficam em memória e são apagados ao reiniciar o servidor.

## O que já funciona

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

Os testes automatizados ainda serão adicionados; o roteiro manual registra as verificações previstas, sem resultados presumidos.

O PDF de especificação está em [`docs/especificacao/VoltaPraMim_Project.pdf`](docs/especificacao/VoltaPraMim_Project.pdf). Consulte também a [arquitetura](docs/arquitetura.md), as [decisões pendentes](docs/decisoes.md) e o [roteiro de teste manual](tests/roteiro-teste-manual.md).
