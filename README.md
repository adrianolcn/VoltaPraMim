# VoltaPraMim

Aplicação web de achados e perdidos para a comunidade acadêmica da UCSal. Este repositório começa a implementação descrita em [`docs/especificacao/VoltaPraMim_Project.pdf`](docs/especificacao/VoltaPraMim_Project.pdf).

> Estado inicial: estrutura de projeto e documentação. As funcionalidades e os testes ainda não foram implementados nem verificados.

## Stack prevista

- Java 21 e Maven 3.9+
- Jakarta Servlet 6 / JSP
- Jetty 12 (Jakarta EE 10) para execução local
- Armazenamento em memória no MVP; H2 é uma evolução opcional
- JUnit 5 para os testes automatizados

## Preparação

Instale um JDK 21 e Maven 3.9 ou superior. Depois, na raiz do projeto:

```sh
mvn test
mvn jetty:run
```

A aplicação ficará disponível em <http://localhost:8080>. Nenhum teste foi executado nesta etapa; o esqueleto de implementação ainda está sendo montado.

## Organização

```text
src/main/java/br/ucsal/voltapramim/
  dominio/          entidades, estados e eventos de domínio
  aplicacao/        casos de uso e portas
  infraestrutura/   repositórios, busca, eventos, fotos e dados de demonstração
  web/              Front Controller, comandos, filtros e rotas
  config/           composição das dependências
src/main/webapp/    JSPs, tags e recursos estáticos
src/test/java/      testes de domínio, padrões e camada web
docs/               arquitetura, validação, design system e decisões
tests/              roteiro de teste manual
```

## Escopo do MVP

1. Cadastrar item achado com referência pública única e estado inicial “Aguardando entrega”.
2. Buscar por texto ignorando caixa e acentos, com filtros por categoria e local.
3. Confirmar recebimento e registrar devolução somente após verificação de posse por atendente.
4. Exibir somente itens ativos na busca pública; documentos não podem receber foto.
5. Demonstrar os padrões State, Observer, Strategy e Command. Decorator para sinônimos é opcional.

O código público localiza um registro, mas não comprova propriedade. O atendimento da Sala de Operações deve ser tratado como simulado até que haja confirmação real.

## Documentação

- [Arquitetura e decisões](docs/arquitetura.md)
- [Registro de decisões pendentes](docs/decisoes.md)
- [Plano de validação](docs/validacao.md)
- [Design System](docs/design-system.md)
- [Declaração de IA e terceiros](docs/declaracao-ia-terceiros.md)
- [Roteiro de teste manual](tests/roteiro-teste-manual.md)

