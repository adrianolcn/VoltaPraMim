<%@ include file="cabecalho.jspf" %>
<section class="form-layout">
    <div class="page-intro">
        <p class="eyebrow">Cadastro rápido</p>
        <h1>Encontrei um item</h1>
        <p>Informe o que encontrou e onde. Não inclua nomes, telefones ou dados pessoais na descrição.</p>
    </div>
    <c:if test="${not empty erro}"><div class="alert alert-error" role="alert"><c:out value="${erro}" /></div></c:if>
    <form class="form-panel" action="<c:url value='/achei' />" method="post">
        <div class="field">
            <label for="titulo">Nome do objeto <span aria-hidden="true">*</span></label>
            <input id="titulo" name="titulo" required maxlength="80" value="<c:out value='${titulo}' />" placeholder="Ex.: garrafa térmica verde">
        </div>
        <div class="field">
            <label for="descricao">Descrição breve <span class="muted">(opcional)</span></label>
            <textarea id="descricao" name="descricao" rows="4" maxlength="500" placeholder="Descreva o objeto sem incluir dados pessoais."><c:out value="${descricao}" /></textarea>
        </div>
        <div class="field-row">
            <div class="field">
                <label for="categoria">Categoria</label>
                <select id="categoria" name="categoria" required>
                    <option value="">Selecione</option>
                    <c:forEach var="categoria" items="${categorias}"><option value="${categoria.name()}"><c:out value="${categoria.rotulo}" /></option></c:forEach>
                </select>
            </div>
            <div class="field">
                <label for="local">Onde encontrou?</label>
                <select id="local" name="local" required>
                    <option value="">Selecione</option>
                    <c:forEach var="local" items="${locais}"><option value="${local.name()}"><c:out value="${local.rotulo}" /></option></c:forEach>
                </select>
            </div>
        </div>
        <div class="form-actions"><button class="button" type="submit">Cadastrar objeto</button><a class="text-link" href="<c:url value='/' />">Cancelar</a></div>
        <p class="form-note">O item aparecerá como “Aguardando entrega”. O código público identifica o registro, mas não comprova propriedade.</p>
    </form>
</section>
<%@ include file="rodape.jspf" %>
