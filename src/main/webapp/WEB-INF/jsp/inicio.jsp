<%@ include file="cabecalho.jspf" %>
<c:url var="buscarUrl" value="/buscar" />
<section class="hero">
    <p class="eyebrow">Achados e perdidos · UCSal</p>
    <h1>Encontrou ou perdeu algo no campus?</h1>
    <p class="hero-copy">Registre um objeto encontrado ou procure entre os itens cadastrados pela comunidade.</p>
    <form class="search-bar" action="${buscarUrl}" method="get">
        <label class="visually-hidden" for="q">O que você está procurando?</label>
        <input id="q" name="q" type="search" maxlength="100" placeholder="Ex.: garrafa, chaveiro, estojo">
        <button class="button" type="submit">Buscar itens</button>
    </form>
    <a class="text-link" href="<c:url value='/achei' />">Encontrei um objeto →</a>
</section>

<section class="section-heading">
    <div>
        <p class="eyebrow">Atualizações recentes</p>
        <h2>Itens cadastrados</h2>
    </div>
    <a class="text-link" href="<c:url value='/buscar' />">Ver todos →</a>
</section>
<c:choose>
    <c:when test="${empty itens}">
        <div class="empty-state"><h3>Ainda não há itens cadastrados</h3><p>Quando alguém registrar um objeto encontrado, ele aparecerá aqui.</p></div>
    </c:when>
    <c:otherwise>
        <div class="item-grid"><c:forEach var="item" items="${itens}"><%@ include file="cartao-item.jspf" %></c:forEach></div>
    </c:otherwise>
</c:choose>
<%@ include file="rodape.jspf" %>
