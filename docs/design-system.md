# Design System

## Diretrizes

- Interface mobile-first, em português do Brasil, com navegação por teclado e foco visível.
- Estados de item sempre apresentados por texto e ícone; nunca comunicar estado só pela cor.
- Mensagens próximas ao campo que precisa de correção, sem falhas previsíveis apresentadas como erro 500.
- Para a categoria Documento, bloquear foto e alertar para não publicar dados pessoais.
- Não exibir instruções de retirada enquanto o item estiver “Aguardando entrega”.

## Componentes disponíveis

Barra de busca, campo de texto, botões, badges de estado, cards de item, campo de upload e alertas estão presentes nas telas. A rota `/design-system` demonstra botões, campo de texto, upload nos estados padrão e erro e os três estados de item. A validação visual completa e os estados de foco/sucesso ainda dependem de inspeção manual.

## Tokens e acessibilidade

Os tokens de cor, tipografia, raio e sombra estão definidos em `src/main/webapp/static/css/tokens.css`; `src/main/webapp/assets/css/tokens.css` importa esses tokens e expõe aliases usados pela interface. Contraste, ordem de foco e leitura em celular ainda precisam de validação manual; capturas só devem ser adicionadas após a verificação real.

## Estados dos componentes

### Upload de foto

O componente de upload é exibido na página `/design-system` com dois estados:

| Estado | Comportamento |
|---|---|
| Padrão | Campo opcional para imagem JPEG ou PNG. |
| Foco | Contorno visível para navegação por teclado. |
| Erro | Borda e mensagem textual informam formato ou tamanho inválido. |

O campo possui altura mínima de 44px, permitindo melhor uso por toque em
dispositivos móveis. O texto do erro é apresentado com `role="alert"` e não
depende somente da cor para comunicar o problema.

O servidor valida JPEG, PNG, tamanho máximo de 5 MiB e bloqueio de foto para
Documentos. Os testes automatizados de armazenamento e cadastro cobrem essas
regras; a confirmação visual das mensagens continua no roteiro manual.

### Badges de status

Os badges representam o ciclo de vida de um Item:

| Estado | Classe CSS | Significado |
|---|---|---|
| Aguardando entrega | `badge-pending` | O objeto foi cadastrado, mas ainda não foi entregue ao atendimento. |
| Disponível para retirada | `badge-available` | O atendimento confirmou o recebimento do objeto. |
| Devolvido | `badge-returned` | O objeto foi devolvido e não deve aparecer na busca ativa. |

Cada badge exibe texto além de cor. Dessa forma, a informação permanece
compreensível para pessoas com limitações de percepção de cor ou quando a tela
for visualizada em escala de cinza.

## Verificação de acessibilidade

Antes da entrega, a equipe deve registrar a execução destas verificações:

- [ ] O campo de upload pode receber foco usando a tecla `Tab`.
- [ ] O foco visível aparece no campo de upload.
- [ ] A mensagem de erro informa claramente o problema do arquivo.
- [ ] Os badges exibem texto legível para todos os estados.
- [ ] A página `/design-system` funciona em tela estreita.
- [ ] As cores de texto e fundo foram verificadas quanto ao contraste.
- [ ] O status do Item não depende somente da cor.
