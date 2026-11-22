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


## Upload de fotos

| Cenário | Passos | Resultado esperado |
|---|---|---|
| Cadastro sem foto | Abrir `/achei`, preencher título, categoria e local; enviar sem selecionar arquivo. | Cadastro é concluído e o detalhe não mostra área de imagem. |
| JPEG válido | Selecionar uma imagem JPEG menor que 5 MiB e cadastrar um item comum. | Cadastro é concluído; o detalhe mostra a imagem. |
| PNG válido | Selecionar uma imagem PNG menor que 5 MiB e cadastrar um item comum. | Cadastro é concluído; o detalhe mostra a imagem. |
| Arquivo inválido | Selecionar um arquivo de texto ou outro formato não permitido. | Formulário mostra mensagem clara; nenhum Item é cadastrado. |
| Arquivo grande | Selecionar imagem maior que 5 MiB. | Formulário mostra mensagem sobre o limite; nenhum Item é cadastrado. |
| Documento com foto | Selecionar categoria Documentos e anexar uma imagem. | Formulário rejeita o cadastro; nenhum arquivo deve ser salvo. |
| Foto inexistente | Abrir `/fotos/5e2edca0-858f-4e4c-827c-29308063a86b`. | Servidor retorna HTTP 404. |
| Foto malformada | Abrir `/fotos/../segredo`. | Servidor retorna HTTP 404 ou rejeita a rota; nenhum arquivo externo é acessado. |

## Fora desta versão

Painel administrativo, recebimento/devolução pela interface, uploads de imagem e proteção CSRF das rotas administrativas serão verificados quando forem implementados.

| Data | Verificação | Resultado real | Evidência / observações |
| --- | --- | --- | --- |
| — | Ainda não executado | — | — |
