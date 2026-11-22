# Roteiro de teste manual

Os itens abaixo descrevem verificações planejadas. Registre resultados somente depois de executá-las; ainda não há resultados anotados.

1. Iniciar a aplicação com `mvn jetty:run` e abrir `http://localhost:8080`.
2. Confirmar a página inicial e os três itens fictícios.
3. Abrir `/buscar`, pesquisar por texto com e sem acentos e combinar categoria/local.
4. Abrir um detalhe e confirmar referência e estado “Aguardando entrega”.
5. Enviar cadastro válido e conferir que o item aparece nos resultados.
6. Tentar enviar campos obrigatórios vazios e título acima de 80 caracteres.
7. Verificar que itens aguardando não prometem retirada.
8. Conferir foco por teclado, labels, navegação no celular e contraste.

## Fora desta versão

Painel administrativo, recebimento/devolução pela interface, uploads de imagem e proteção CSRF das rotas administrativas serão verificados quando forem implementados.

| Data | Verificação | Resultado real | Evidência / observações |
| --- | --- | --- | --- |
| — | Ainda não executado | — | — |
