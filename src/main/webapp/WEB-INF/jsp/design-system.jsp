<%@ page pageEncoding="UTF-8" %>
<%@ include file="cabecalho.jspf" %>
<div class="design-system-page">
    <section class="page-intro">
        <p class="eyebrow">Interface</p>
        <h1>Design System</h1>
        <p>Componentes e estados usados atualmente no VoltaPraMim.</p>
    </section>

    <section class="ds-section" aria-labelledby="ds-foundations-title">
        <div class="ds-section-heading">
            <div>
                <p class="eyebrow">Fundamentos</p>
                <h2 id="ds-foundations-title">Cores da interface</h2>
            </div>
            <p>Uma paleta quente com verde escuro e laranja para destacar ações e estados.</p>
        </div>
        <div class="ds-color-grid">
            <div class="ds-color-swatch ds-color-background"><span>Fundo</span><code>--background</code></div>
            <div class="ds-color-swatch ds-color-primary"><span>Primária</span><code>--primary</code></div>
            <div class="ds-color-swatch ds-color-signal"><span>Sinalização</span><code>--signal</code></div>
            <div class="ds-color-swatch ds-color-card"><span>Cartão</span><code>--card</code></div>
        </div>
    </section>

    <section class="ds-section" aria-labelledby="ds-components-title">
        <div class="ds-section-heading">
            <div>
                <p class="eyebrow">Componentes</p>
                <h2 id="ds-components-title">Ações, status e campos</h2>
            </div>
            <p>Exemplos seguem os estilos aplicados na busca, no cadastro e nos cartões de itens.</p>
        </div>

        <div class="ds-grid ds-grid-three">
            <article class="ds-panel">
                <h3>Botões</h3>
                <div class="ds-button-stack">
                    <button class="ds-button ds-button-primary" type="button">Cadastrar objeto <span aria-hidden="true">→</span></button>
                    <button class="ds-button ds-button-secondary" type="button">Ação secundária</button>
                    <button class="ds-filter-chip" type="button" aria-pressed="true"><span aria-hidden="true">▦</span> Todos</button>
                </div>
            </article>

            <article class="ds-panel">
                <h3>Status do item</h3>
                <div class="ds-status-stack">
                    <span class="status-badge status-waiting"><span class="status-dot" aria-hidden="true"></span>Aguardando entrega</span>
                    <span class="status-badge status-available"><span class="status-dot" aria-hidden="true"></span>Disponível para retirada</span>
                    <span class="status-badge status-returned"><span class="status-dot" aria-hidden="true"></span>Devolvido</span>
                </div>
            </article>

            <article class="ds-panel">
                <h3>Campos</h3>
                <div class="ds-field">
                    <label for="ds-text">Nome do objeto</label>
                    <input id="ds-text" type="text" placeholder="Ex.: garrafa térmica verde">
                </div>
                <div class="ds-field">
                    <label for="ds-category">Categoria</label>
                    <select id="ds-category">
                        <option>Selecione a categoria</option>
                        <option>Eletrônicos</option>
                    </select>
                </div>
            </article>
        </div>
    </section>

    <section class="ds-section" aria-labelledby="ds-card-title">
        <div class="ds-section-heading">
            <div>
                <p class="eyebrow">Componente</p>
                <h2 id="ds-card-title">Cartão de item</h2>
            </div>
            <p>O estado, o local e a referência ficam visíveis antes de abrir os detalhes.</p>
        </div>
        <article class="ds-item-card">
            <div class="ds-item-meta">
                <span class="status-badge status-waiting"><span class="status-dot" aria-hidden="true"></span>Aguardando entrega</span>
            </div>
            <h3>Garrafa térmica verde</h3>
            <p class="ds-item-location"><span aria-hidden="true">📍</span> Biblioteca · Outros</p>
            <div class="ds-item-footer">
                <span class="ds-item-reference">VPM-R7MFP</span>
                <a href="#ds-card-title">Ver detalhes <span aria-hidden="true">→</span></a>
            </div>
        </article>
    </section>

    <section class="ds-section" aria-labelledby="ds-operations-title">
        <div class="ds-section-heading">
            <div>
                <p class="eyebrow">Operações</p>
                <h2 id="ds-operations-title">Painel de atendimento</h2>
            </div>
            <p>Resumo por etapa, filas de trabalho e ações que deixam claro o próximo passo do atendimento.</p>
        </div>

        <div class="ds-ops-summary" aria-label="Resumo das etapas">
            <article class="ds-ops-summary-card ds-ops-summary-waiting">
                <span class="ds-ops-summary-icon" aria-hidden="true">◷</span>
                <div><span class="ds-ops-summary-label">Aguardando entrega</span><strong>03</strong></div>
                <p>Aguardam confirmação presencial</p>
            </article>
            <article class="ds-ops-summary-card ds-ops-summary-available">
                <span class="ds-ops-summary-icon" aria-hidden="true">✓</span>
                <div><span class="ds-ops-summary-label">Disponíveis para retirada</span><strong>02</strong></div>
                <p>Aguardam identificação do proprietário</p>
            </article>
            <article class="ds-ops-summary-card ds-ops-summary-returned">
                <span class="ds-ops-summary-icon" aria-hidden="true">↗</span>
                <div><span class="ds-ops-summary-label">Devolvidos</span><strong>08</strong></div>
                <p>Concluídos nesta sessão</p>
            </article>
        </div>

        <div class="ds-ops-workflow-grid">
            <article class="ds-ops-stage">
                <header class="ds-ops-stage-heading">
                    <div><span class="ds-ops-step">Etapa 1</span><h3>Aguardando entrega</h3></div>
                    <span class="ds-ops-count">1 item</span>
                </header>
                <div class="ds-ops-item">
                    <div class="ds-ops-item-info">
                        <span class="ds-ops-reference">VPM-G5Z4N · Acessórios</span>
                        <h4>Estojo preto</h4>
                        <p>Sala de aula</p>
                    </div>
                    <button class="ds-button ds-button-primary ds-ops-action" type="button">Confirmar recebimento <span aria-hidden="true">→</span></button>
                </div>
            </article>

            <article class="ds-ops-stage">
                <header class="ds-ops-stage-heading">
                    <div><span class="ds-ops-step">Etapa 2</span><h3>Disponíveis para retirada</h3></div>
                    <span class="ds-ops-count">1 item</span>
                </header>
                <div class="ds-ops-item">
                    <div class="ds-ops-item-info">
                        <span class="ds-ops-reference">VPM-TG7XS · Outros</span>
                        <h4>Garrafa térmica verde</h4>
                        <p>Biblioteca</p>
                    </div>
                    <form class="ds-ops-return-form">
                        <label><input type="checkbox"> Posse verificada presencialmente</label>
                        <button class="ds-button ds-button-secondary ds-ops-action" type="button">Registrar devolução <span aria-hidden="true">→</span></button>
                    </form>
                </div>
            </article>
        </div>
    </section>

    <section class="ds-section" aria-labelledby="ds-upload-title">
        <div class="ds-section-heading">
            <div>
                <p class="eyebrow">Componente</p>
                <h2 id="ds-upload-title">Upload de foto</h2>
            </div>
            <p>O envio é opcional e aceita JPEG ou PNG de até 5 MiB. Documentos não recebem foto.</p>
        </div>
        <div class="ds-grid ds-grid-two">
            <article class="ds-panel ds-upload-panel">
                <h3>Estado padrão</h3>
                <div class="ds-photo-drop">
                    <span class="ds-camera-icon" aria-hidden="true">▧</span>
                    <strong>Adicione uma foto do objeto</strong>
                    <span>ou arraste uma imagem aqui</span>
                    <input class="ds-photo-input" id="ds-photo" type="file" accept="image/jpeg,image/png" aria-describedby="ds-photo-help">
                    <label class="ds-button ds-button-secondary" for="ds-photo">Escolher foto</label>
                </div>
                <p class="ds-help" id="ds-photo-help">JPEG ou PNG · Até 5 MiB</p>
            </article>
            <article class="ds-panel ds-upload-panel">
                <h3>Estado de erro</h3>
                <div class="ds-photo-drop ds-photo-drop-error" aria-invalid="true">
                    <span class="ds-camera-icon" aria-hidden="true">▧</span>
                    <strong>Não foi possível usar este arquivo</strong>
                    <span>Escolha uma imagem JPEG ou PNG de até 5 MiB.</span>
                </div>
                <p class="ds-error-message">O formato ou tamanho do arquivo não é permitido.</p>
            </article>
        </div>
    </section>
</div>

<%@ include file="rodape.jspf" %>
