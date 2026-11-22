<%@ page pageEncoding="UTF-8" %>
<%@ include file="cabecalho.jspf" %>
<c:url var="buscarUrl" value="/buscar" />
<p><a class="text-link" href="${buscarUrl}">← Voltar à busca</a></p>
<article class="detail-panel">
    <div class="item-card-top">
        <c:choose>
            <c:when test="${item.estadoRotulo eq 'Disponível para retirada'}">
                <span class="badge badge-available">
                    <c:out value="${item.estadoRotulo}" />
                </span>
            </c:when>

            <c:when test="${item.estadoRotulo eq 'Devolvido'}">
                <span class="badge badge-returned">
                    <c:out value="${item.estadoRotulo}" />
                </span>
            </c:when>

            <c:otherwise>
                <span class="badge badge-pending">
                    <c:out value="${item.estadoRotulo}" />
                </span>
            </c:otherwise>
        </c:choose>
        <span class="item-category"><c:out value="${item.categoria.rotulo}" /></span>
    </div>
    <h1><c:out value="${item.titulo}" /></h1>
    <c:if test="${not empty item.descricao}"><p class="detail-description"><c:out value="${item.descricao}" /></p></c:if>
    <dl class="detail-list">
        <div><dt>Local encontrado</dt><dd><c:out value="${item.local.rotulo}" /></dd></div>
        <div><dt>Encontrado em</dt><dd><c:out value="${dataEncontradoEm}" /></dd></div>
        <div><dt>Referência</dt><dd><c:out value="${item.referenciaPublica}" /></dd></div>
        <div><dt>Status</dt><dd><c:out value="${item.estadoRotulo}" /></dd></div>
    </dl>
    <p class="notice">Este protótipo não confirma a posse nem informa local de retirada. A referência serve apenas para localizar o registro.</p>
</article>
<%@ include file="rodape.jspf" %>
