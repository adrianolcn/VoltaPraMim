# Design System

## Diretrizes

- Interface mobile-first, em português do Brasil, com navegação por teclado e foco visível.
- Estados de item sempre apresentados por texto e ícone; nunca comunicar estado só pela cor.
- Mensagens próximas ao campo que precisa de correção, sem falhas previsíveis apresentadas como erro 500.
- Para a categoria Documento, bloquear foto e alertar para não publicar dados pessoais.
- Não exibir instruções de retirada enquanto o item estiver “Aguardando entrega”.

## Componentes previstos

Barra de busca, campo de texto, botão, badge de estado, card de item, campo de upload e alerta. A rota `/design-system` deverá demonstrar estados normal, foco, erro e sucesso.

## Tokens e acessibilidade

Definir cores, tipografia, escala de espaçamento e contraste em `src/main/webapp/assets/css/tokens.css` durante a implementação. Validar contraste, labels, ordem de foco e leitura em celular; capturas só devem ser adicionadas após a interface existir.
