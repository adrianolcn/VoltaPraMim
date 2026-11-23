# Arquitetura

## Estado da primeira versão

O fluxo público inclui cadastro em memória, página inicial, busca por relevância ou recentes, consulta de detalhes e fotos opcionais. `AppBootstrap` cria um repositório compartilhado e carrega os dados fictícios uma única vez. `FrontControllerServlet` e `Roteador` despacham rotas públicas e administrativas para seus comandos. O cadastro, a busca, os detalhes e o painel usam o mesmo repositório. O painel implementa autenticação demonstrativa, CSRF, recebimento e devolução com atualização atômica por item.

O projeto está configurado para Java 21, conforme a especificação. `mvn clean verify` e um smoke test HTTP do Jetty foram executados com OpenJDK 21.0.13.1; os resultados estão em `docs/validacao.md`.

O plugin Jetty limita a varredura dos JARs da aplicação para não escanear novamente JSTL já disponibilizado pelo contêiner. Os JARs JSTL continuam no WAR para implantação em contêineres externos.

## Direção das dependências

O sistema é organizado em camadas. O domínio contém as regras do ciclo de vida do item e não depende de Servlet, JSP, repositórios, uploads ou bibliotecas de infraestrutura. Os casos de uso dependem de portas; as implementações concretas são conectadas na Composition Root em `config`.

```text
web → aplicação → domínio
        ↑    ↑
 infraestrutura
        ↑
      config (monta as dependências)
```

## Limites do MVP

- Repositório em memória; itens são descartados ao reiniciar o servidor.
- Fotos são armazenadas fora da área web com UUID e validação de conteúdo e tamanho (limite de 5 MiB); a limpeza dos arquivos não é automatizada.
- Dados demonstrativos sempre fictícios.
- Painel de operações é uma simulação acadêmica com senha compartilhada configurada via `VPM_OPERACOES_SENHA`; não há contas individuais, integração institucional ou aceite formal da Sala de Operações.
- Sessões usam cookie `HttpOnly` e expiram após 30 minutos; mutações administrativas e login exigem token CSRF.
- Nenhum dado pessoal de quem encontrou aparece no domínio ou na interface pública.

## Padrões a demonstrar

| Padrão | Local | Comportamento esperado |
| --- | --- | --- |
| State | `dominio/estado` | Transições aguardando → disponível → devolvido; saltos e repetições são rejeitados. |
| Observer | `dominio/evento`, `infraestrutura/eventos` | Publica eventos após persistir; auditoria e estatísticas reagem independentemente. |
| Strategy | `aplicacao/porta`, `infraestrutura/busca` | Busca por relevância para texto e itens recentes sem termo. |
| Command | `web/comandos` | O Front Controller despacha cada ação HTTP para um comando. |
| Decorator | `infraestrutura/busca/BuscaComSinonimos` | Envolve `MotorDeBusca`, consulta termos equivalentes (celular ↔ smartphone) e combina resultados sem duplicar itens. |

Repository, Front Controller e Composition Root são escolhas arquiteturais; não serão apresentados como padrões GoF.

`AppBootstrap` envolve `BuscaPorRelevancia` com `BuscaComSinonimos`. A busca original continua responsável pela
normalização, relevância e filtros; o Decorator apenas executa consultas equivalentes e reúne os resultados.
`BuscaRecentes` permanece sem decoração, pois não recebe texto de busca.

## Fluxo principal

`POST /achei` → comando web → caso de uso `CadastrarItem` → repositório → publicação de `ItemCadastrado` → observers → resposta HTTP.

As regras e critérios detalhados estão no PDF de especificação incluído em [`especificacao/VoltaPraMim_Project.pdf`](especificacao/VoltaPraMim_Project.pdf).
