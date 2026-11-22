<%@ include file="cabecalho.jspf" %>
<c:url var="buscarUrl" value="/buscar" />
<section class="page-intro">
    <p class="eyebrow">Consulta pública</p>
    <h1>Buscar itens</h1>
    <p>Pesquise por nome, descrição, categoria ou local. A busca ignora acentos e maiúsculas.</p>
</section>
<form class="filter-panel" action="${buscarUrl}" method="get">
    <div class="field field-grow">
        <label for="q">Palavra-chave</label>
        <input id="q" name="q" type="search" maxlength="100" value="<c:out value='${texto}' />" placeholder="Ex.: garrafa térmica">
    </div>
    <div class="field">
        <label for="categoria">Categoria</label>
        <select id="categoria" name="categoria">
            <option value="">Todas</option>
            <c:forEach var="categoria" items="${categorias}">
                <option value="${categoria.name()}" ${categoria.name() == categoriaSelecionada ? 'selected' : ''}><c:out value="${categoria.rotulo}" /></option>
            </c:forEach>
        </select>
    </div>
    <div class="field">
        <label for="local">Local</label>
        <select id="local" name="local">
            <option value="">Todos</option>
            <c:forEach var="local" items="${locais}">
                <option value="${local.name()}" ${local.name() == localSelecionado ? 'selected' : ''}><c:out value="${local.rotulo}" /></option>
            </c:forEach>
        </select>
    </div>
    <button class="button" type="submit">Aplicar filtros</button>
</form>
<section aria-live="polite">
    <div class="section-heading results-heading"><h2><c:out value="${itens.size()}" /> resultado(s)</h2></div>
    <c:choose>
        <c:when test="${empty itens}"><div class="empty-state"><h3>Nenhum item encontrado</h3><p>Tente outros termos ou remova algum filtro.</p></div></c:when>
        <c:otherwise><div class="item-grid"><c:forEach var="item" items="${itens}"><%@ include file="cartao-item.jspf" %></c:forEach></div></c:otherwise>
    </c:choose>
</section>
<%@ include file="rodape.jspf" %>
