# Plano de validação

As hipóteses de produto abaixo dependem de testes externos com usuários e conversa institucional. Não há resultados de pesquisa de produto registrados. A seção seguinte deve conter apenas verificações técnicas realmente executadas e evidências reproduzíveis.

| Hipótese | Método | Evidência a registrar | Estado |
| --- | --- | --- | --- |
| Quem encontra aceita cadastrar um item em menos de um minuto. | Teste cronometrado com pessoas fora da equipe. | Tempo observado, contexto e dificuldades, sem dados pessoais. | Não iniciado |
| Quem perdeu consultaria o site sem login. | Formulário anônimo e teste guiado. | Tarefas concluídas, dúvidas e observações. | Não iniciado |
| Sala de Operações pode centralizar a retirada. | Conversa com responsável e aceite operacional. | Data, papel do interlocutor e decisão; se não confirmado, manter simulação. | Simulado (sem aceite formal) |

## Verificação técnica e funcional

| Data | Verificação | Resultado real | Evidência / observações |
| --- | --- | --- | --- |
| 23/11/2026 | Suíte automatizada JUnit (`mvn test`) | Aprovado (58 testes; 0 falhas, 0 erros, 0 ignorados) | Executado nesta revisão no worktree `pr18-review`. |
| 23/11/2026 | Empacotamento (`mvn package`) | Aprovado | WAR gerado em `target/voltapramim.war` nesta revisão. |
| — | Fluxo completo no navegador (`mvn jetty:run`) | Pendente | O roteiro manual descreve os passos; navegador/Jetty não foram verificados nesta revisão. |
