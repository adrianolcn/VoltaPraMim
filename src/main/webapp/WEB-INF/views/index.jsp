<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                <form class="search-box" id="search-form" action="#itens" role="search">
                    <label class="visually-hidden" for="item-search">Buscar por nome ou local</label>
                    <input id="item-search" type="search" placeholder="Ex.: garrafa, chaveiro, estojo" autocomplete="off">
                    <button class="button button-primary" type="submit">Buscar itens</button>
                </form>
            </div>

            <aside class="found-tag" aria-label="Resumo dos itens encontrados">
                <div class="tag-label">Achado em</div>
                <div class="tag-place">Biblioteca</div>
                <div class="tag-rule" aria-hidden="true"></div>
                <div class="tag-count"><c:out value="${itemCount}"/> itens esperando o dono</div>
                <div class="tag-reference">REF · VPM-QM9QS</div>
            </aside>
        </section>

        <section class="items-section" id="itens" aria-labelledby="items-title">
            <div class="section-heading">
                <div class="section-title">
                    <p class="eyebrow">Atualizações recentes</p>
                    <h2 id="items-title">Itens cadastrados</h2>
                </div>
                <div class="category-chips" role="group" aria-label="Filtrar por categoria">
                    <button class="category-chip is-active" type="button" data-category-filter="Todos" aria-pressed="true">Todos</button>
                    <button class="category-chip" type="button" data-category-filter="Acessórios" aria-pressed="false">Acessórios</button>
                    <button class="category-chip" type="button" data-category-filter="Chaves" aria-pressed="false">Chaves</button>
                    <button class="category-chip" type="button" data-category-filter="Eletrônicos" aria-pressed="false">Eletrônicos</button>
                    <button class="category-chip" type="button" data-category-filter="Outros" aria-pressed="false">Outros</button>
                </div>
            </div>

            <p class="visually-hidden" id="results-announcement" aria-live="polite"></p>
            <div class="items-grid" id="items-grid">
                <c:forEach var="item" items="${items}">
                    <article class="item-card" data-item-card
                             data-title="<c:out value='${item.title}'/>"
                             data-place="<c:out value='${item.place}'/>"
                             data-category="<c:out value='${item.category}'/>">
                        <div class="item-meta">
                            <c:choose>
                                <c:when test="${item.status eq 'aguardando'}">
                                    <span class="status-badge status-waiting"><span class="status-dot" aria-hidden="true"></span>Aguardando dono</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="status-badge status-returned"><span class="status-dot" aria-hidden="true"></span>Devolvido</span>
                                </c:otherwise>
                            </c:choose>
                            <span class="item-when"><c:out value="${item.when}"/></span>
                        </div>
                        <h3 class="item-title"><c:out value="${item.title}"/></h3>
                        <p class="item-location"><span aria-hidden="true">📍</span> <c:out value="${item.place}"/> · <c:out value="${item.category}"/></p>
                        <div class="item-footer">
                            <span class="item-reference"><c:out value="${item.ref}"/></span>
                            <a class="item-claim" href="#registrar">É meu <span aria-hidden="true">→</span></a>
                        </div>
                    </article>
                </c:forEach>
                <div class="empty-state" id="empty-state" hidden>
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
