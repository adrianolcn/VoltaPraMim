# Decisões, estado e limites do MVP

Este registro separa regras definidas no guia de decisões que ainda precisam de confirmação da equipe ou possuem limites declarados no protótipo.

| Tema | Direção inicial | Estado |
| --- | --- | --- |
| Atendimento da Sala de Operações | Fluxo implementado como simulação acadêmica em `/operacoes`, sem aceite institucional formal nem integração com sistemas da UCSal. | Simulado |
| Credencial e sessão administrativa | Senha de demonstração lida exclusivamente via `VPM_OPERACOES_SENHA`, sessão `HttpOnly` com expiração de 30 minutos; sem contas individuais. | Implementado (MVP) |
| Proteção CSRF no painel | Token vinculado à sessão validado no servidor em todos os `POST` sob `/operacoes` (`401` sem sessão, `403` sem token válido). | Implementado |
| Local e horário de retirada | Instrução de retirada exibida em `/itens/{id}` apenas após confirmação de recebimento (`DISPONIVEL_RETIRADA`); horários fixos dependem de aceite. | Parcial |
| Persistência após reinício | MVP usa memória, conforme a especificação; dados são recriados a cada inicialização. H2 fica como evolução futura, não como pendência desta entrega. | Decidido fora do MVP |
| Versão do Java | Maven configurado com `maven.compiler.release=21`, conforme a especificação. `mvn clean verify` e smoke test HTTP do Jetty passaram com OpenJDK 21.0.13.1. | Implementado e validado |
| Verificação de posse | Exige confirmação explícita do atendente após conferência presencial de característica não publicada; referência `VPM-XXXXX` apenas localiza o registro. | Implementado |
| Moderação do conteúdo público | Definir regras e limites antes de qualquer uso público real. A instância atual é demonstração acadêmica com dados fictícios. | Pendente para produção; fora do MVP |
| Busca com sinônimos | `BuscaComSinonimos` envolve `BuscaPorRelevancia`; contempla `celular` ↔ `smartphone`, mantém filtros e elimina itens duplicados. Testes automatizados aprovados. | Implementado; ampliar vocabulário se necessário |
