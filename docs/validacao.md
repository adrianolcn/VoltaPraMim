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
| 23/11/2026 | Java 21 (`mvn clean verify`) | Aprovado (58 testes; 0 falhas, 0 erros, 0 ignorados; WAR empacotado) | Executado com OpenJDK 21.0.13.1; compilação principal e testes usaram `--release 21`. |
| 23/11/2026 | Smoke test HTTP do Jetty (`mvn jetty:run`) | Aprovado: `/`, `/buscar`, `/achei`, `/design-system` e `/operacoes/login` responderam HTTP 200 | Jetty 12 iniciou usando JVM 21.0.13.1. Após restringir a varredura dos JARs JSTL no Jetty, os avisos de classes em múltiplas localizações desapareceram. Permanecem apenas avisos não fatais sobre schemas XML externos; o roteiro manual completo ainda não foi executado. |
