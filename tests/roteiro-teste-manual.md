# Roteiro de teste manual

Este roteiro define verificações a executar. Nenhum resultado foi observado nesta etapa.

1. Clonar o repositório e seguir o README em ambiente limpo.
2. Executar `mvn test` e registrar a saída real.
3. Iniciar com `mvn jetty:run` e confirmar as telas no navegador.
4. Cadastrar item válido e confirmar o estado inicial “Aguardando entrega”.
5. Buscar sem acentos e combinar texto, categoria e local.
6. Confirmar que item aguardando não apresenta instrução de retirada.
7. Tentar devolver antes de confirmar recebimento; a transição deve ser rejeitada.
8. Confirmar recebimento e depois registrar devolução com posse verificada.
9. Confirmar que itens devolvidos saem da busca pública ativa.
10. Tentar acesso sem sessão e POST sem CSRF nas rotas de operações.
11. Enviar arquivo inválido, arquivo acima de 5 MB e foto para categoria Documento.
12. Verificar teclado, foco, labels, contraste e mensagens em celular.

| Data | Verificação | Resultado real | Evidência / observações |
| --- | --- | --- | --- |
| — | Ainda não executado | — | — |
