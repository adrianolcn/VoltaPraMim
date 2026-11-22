<%@ include file="cabecalho.jspf" %>
<section class="page-intro">
    <p class="eyebrow">Interface</p>
    <h1>Design System</h1>
    <p>Componentes iniciais do protótipo, com cores, tipografia, foco e estados visíveis.</p>
</section>
<section class="design-samples">
    <div class="sample-card"><h2>Botões</h2><button class="button" type="button">Ação principal</button> <a class="button button-secondary" href="<c:url value='/achei' />">Ação secundária</a></div>
    <div class="sample-card"><h2>Estados</h2><span class="badge badge-pending">◷ Aguardando entrega</span></div>
    <div class="sample-card"><h2>Campos</h2><label for="sample-field">Campo de texto</label><input id="sample-field" type="text" placeholder="Exemplo"></div>
</section>
<%@ include file="rodape.jspf" %>
