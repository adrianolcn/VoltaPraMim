# Arquitetura

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

- Repositório em memória, com atualização atômica por item.
- Fotos armazenadas fora da árvore pública e validadas por conteúdo e tamanho (limite de 5 MB).
- Dados demonstrativos sempre fictícios.
- Painel de operações com sessão e proteção CSRF; o fluxo permanece simulado até confirmação institucional.
- Nenhum dado pessoal de quem encontrou aparece no domínio ou na interface pública.

## Padrões a demonstrar

| Padrão | Local | Comportamento esperado |
| --- | --- | --- |
| State | `dominio/estado` | Transições aguardando → disponível → devolvido; saltos e repetições são rejeitados. |
| Observer | `dominio/evento`, `infraestrutura/eventos` | Publica eventos após persistir; auditoria e estatísticas reagem independentemente. |
| Strategy | `aplicacao/porta`, `infraestrutura/busca` | Busca por relevância para texto e itens recentes sem termo. |
| Command | `web/comandos` | O Front Controller despacha cada ação HTTP para um comando. |
| Decorator (opcional) | `infraestrutura/busca` | Expande sinônimos e delega à estratégia existente. |

Repository, Front Controller e Composition Root são escolhas arquiteturais; não serão apresentados como padrões GoF.

## Fluxo principal

`POST /achei` → comando web → caso de uso `CadastrarItem` → fábrica e repositório → publicação de `ItemCadastrado` → observers → resposta HTTP.

As regras e critérios detalhados estão no PDF de especificação incluído em [`especificacao/VoltaPraMim_Project.pdf`](especificacao/VoltaPraMim_Project.pdf).
