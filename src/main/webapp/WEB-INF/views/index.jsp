<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>VoltaPraMim · Achados e perdidos UCSal</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Bricolage+Grotesque:wght@500;700;800&family=Instrument+Sans:wght@400;600;700&family=JetBrains+Mono:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css">
</head>
<body>
<div class="page-shell">
    <header class="site-header">
        <a class="brand" href="${pageContext.request.contextPath}/" aria-label="VoltaPraMim, início">
            <span class="brand-mark" aria-hidden="true">↩</span>
            <span class="brand-name">VoltaPraMim</span>
        </a>
        <nav class="header-actions" aria-label="Navegação principal">
            <a class="nav-link" href="#itens">Buscar</a>
            <a class="button button-accent header-cta" href="#registrar">Encontrei um item</a>
        </nav>
    </header>

    <main>
        <section class="hero" aria-labelledby="hero-title">
            <div class="hero-copy">
                <p class="eyebrow">Achados e perdidos · UCSal</p>
                <h1 id="hero-title">Perdeu algo no campus? <span>Volta pra você.</span></h1>
                <p class="hero-description">Procure entre os objetos encontrados pela comunidade ou registre algo que você achou.</p>
                <form class="search-box" id="search-form" action="${pageContext.request.contextPath}/buscar" method="get" role="search">
                    <label class="visually-hidden" for="item-search">Buscar por nome ou local</label>
                    <input id="item-search" name="q" type="search" placeholder="Ex.: garrafa, chaveiro, estojo" autocomplete="off">
                    <button class="button button-primary" type="submit">Buscar itens</button>
                </form>
            </div>

            <aside class="found-tag" aria-label="Resumo dos itens encontrados">
                <c:choose>
                    <c:when test="${not empty itens}">
                        <div class="tag-label">Achado em</div>
                        <div class="tag-place"><c:out value="${itens[0].local.rotulo}"/></div>
                        <div class="tag-rule" aria-hidden="true"></div>
                        <div class="tag-count"><c:out value="${fn:length(itens)}"/> itens cadastrados</div>
                        <div class="tag-reference">REF · <c:out value="${itens[0].referenciaPublica}"/></div>
                    </c:when>
                    <c:otherwise>
                        <div class="tag-label">Achados e perdidos</div>
                        <div class="tag-rule" aria-hidden="true"></div>
                        <div class="tag-count">Nenhum item cadastrado</div>
                    </c:otherwise>
                </c:choose>
            </aside>
        </section>

        <section class="items-section" id="itens" aria-labelledby="items-title">
            <div class="section-heading">
                <div class="section-title">
                    <p class="eyebrow">Atualizações recentes</p>
                    <h2 id="items-title">Itens cadastrados</h2>
                </div>
                <div class="category-chips" role="group" aria-label="Filtrar por categoria">
                    <button class="category-chip is-active" type="button" data-category-filter="Todos" aria-pressed="true"><svg class="category-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" aria-hidden="true"><rect x="3" y="3" width="7" height="7" rx="1"/><rect x="14" y="3" width="7" height="7" rx="1"/><rect x="3" y="14" width="7" height="7" rx="1"/><rect x="14" y="14" width="7" height="7" rx="1"/></svg><span>Todos</span></button>
                    <c:forEach var="categoria" items="${categorias}">
                        <button class="category-chip" type="button" data-category-filter="<c:out value='${categoria.name()}'/>" aria-pressed="false">
                            <svg class="category-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true">
                                <c:choose>
                                    <c:when test="${categoria.name() eq 'CELULAR'}"><rect x="6" y="2" width="12" height="20" rx="2"/><path d="M10 18h4"/></c:when>
                                    <c:when test="${categoria.name() eq 'CHAVES'}"><circle cx="8" cy="8" r="4"/><path d="m11 11 9 9m-3-3 2-2m-5-1 2-2"/></c:when>
                                    <c:when test="${categoria.name() eq 'DOCUMENTOS'}"><path d="M6 3h8l4 4v14H6z"/><path d="M14 3v5h5M9 12h6M9 16h6"/></c:when>
                                    <c:when test="${categoria.name() eq 'BOLSAS'}"><rect x="5" y="7" width="14" height="14" rx="3"/><path d="M9 7V5a3 3 0 0 1 6 0v2M8 14h8v4H8z"/></c:when>
                                    <c:when test="${categoria.name() eq 'ACESSORIOS'}"><circle cx="12" cy="12" r="7"/><path d="m12 3 2 2-2 2-2-2 2-2Z"/></c:when>
                                    <c:otherwise><circle cx="5" cy="12" r="1"/><circle cx="12" cy="12" r="1"/><circle cx="19" cy="12" r="1"/></c:otherwise>
                                </c:choose>
                            </svg><span><c:out value="${categoria.rotulo}"/></span>
                        </button>
                    </c:forEach>
                </div>
            </div>

            <p class="visually-hidden" id="results-announcement" aria-live="polite"></p>
            <div class="items-grid" id="items-grid">
                <c:forEach var="item" items="${itens}">
                    <article class="item-card" data-item-card
                             data-title="<c:out value='${item.titulo}'/>"
                             data-place="<c:out value='${item.local.rotulo}'/>"
                             data-category="<c:out value='${item.categoria.name()}'/>">
                        <div class="item-meta">
                            <span class="status-badge ${item.estadoRotulo eq 'Disponível para retirada' ? 'status-available' : 'status-waiting'}"><span class="status-dot" aria-hidden="true"></span><c:out value="${item.estadoRotulo}"/></span>
                        </div>
                        <h3 class="item-title"><c:out value="${item.titulo}"/></h3>
                        <p class="item-location"><span aria-hidden="true">📍</span> <c:out value="${item.local.rotulo}"/> · <c:out value="${item.categoria.rotulo}"/></p>
                        <div class="item-footer">
                            <span class="item-reference"><c:out value="${item.referenciaPublica}"/></span>
                            <c:url var="detalheUrl" value="/itens/${item.id}"/>
                            <a class="item-claim" href="<c:out value='${detalheUrl}'/>">Ver detalhes <span aria-hidden="true">→</span></a>
                        </div>
                    </article>
                </c:forEach>
                <div class="empty-state" id="empty-state" ${not empty itens ? "hidden" : ""}>
                    Nenhum item encontrado. Tente outra palavra.
                </div>
            </div>
        </section>

        <section class="register-cta" id="registrar" aria-labelledby="register-title">
            <div>
                <h2 id="register-title">Achou algo por aí?</h2>
                <p>Registre em menos de um minuto e ajude alguém a recuperar o que perdeu.</p>
            </div>
            <a class="button button-accent register-button" href="${pageContext.request.contextPath}/achei">Encontrei um objeto <span aria-hidden="true">→</span></a>
        </section>
    </main>
</div>

<footer class="site-footer">
    <div class="footer-inner">
        <p>Protótipo acadêmico · UCSal</p>
        <a href="${pageContext.request.contextPath}/design-system">Design System</a>
    </div>
</footer>
<script src="${pageContext.request.contextPath}/static/js/home.js" defer></script>
</body>
</html>
