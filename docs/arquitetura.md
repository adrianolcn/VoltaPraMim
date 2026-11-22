# Arquitetura

## Estado da primeira versão

O fluxo público inclui cadastro em memória, página inicial, busca por relevância ou recentes e consulta de detalhes. `AppBootstrap` cria um repositório compartilhado e carrega os dados fictícios uma única vez. `FrontControllerServlet` e `Roteador` despacham `/` e `/home` para `InicioComando`, que usa `BuscarItens` e encaminha os itens ativos à nova JSP. O cadastro e os detalhes usam o mesmo repositório. Fotos, painel de operações e transições administrativas pela interface ficam para etapas seguintes.

O projeto usa Java 17; a especificação revisada menciona Java 21. A versão definitiva permanece uma decisão a confirmar.

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

- Repositório em memória; atualização atômica de transições por item ainda precisa ser implementada.
- Fotos privadas com validação por conteúdo e tamanho (limite de 5 MB) estão pendentes.
- Dados demonstrativos sempre fictícios.
- Painel de operações com sessão e proteção CSRF está pendente; o fluxo será simulado até confirmação institucional.
- Nenhum dado pessoal de quem encontrou aparece no domínio ou na interface pública.

## Padrões a demonstrar

| Padrão | Local | Comportamento esperado |
| --- | --- | --- |
| State | `dominio/estado` | Transições aguardando → disponível → devolvido; saltos e repetições são rejeitados. |
| Observer | `dominio/evento`, `infraestrutura/eventos` | Publica eventos após persistir; auditoria e estatísticas reagem independentemente. |
| Strategy | `aplicacao/porta`, `infraestrutura/busca` | Busca por relevância para texto e itens recentes sem termo. |
| Command | `web/comandos` | O Front Controller despacha cada ação HTTP para um comando. |
| Decorator (opcional) | Pendente | Expansão de sinônimos poderá ser considerada futuramente. |

Repository, Front Controller e Composition Root são escolhas arquiteturais; não serão apresentados como padrões GoF.

## Fluxo principal

`POST /achei` → comando web → caso de uso `CadastrarItem` → repositório → publicação de `ItemCadastrado` → observers → resposta HTTP.

As regras e critérios detalhados estão no PDF de especificação incluído em [`especificacao/VoltaPraMim_Project.pdf`](especificacao/VoltaPraMim_Project.pdf).
