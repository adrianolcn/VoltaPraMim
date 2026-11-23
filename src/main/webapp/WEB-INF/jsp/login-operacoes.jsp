<%@ page pageEncoding="UTF-8" %>
<%@ include file="cabecalho.jspf" %>
<c:url var="loginAction" value="/operacoes/login" />
<c:url var="inicioUrl" value="/" />

<div class="operations-page ops-login-page">
    <a class="ops-back-link" href="${inicioUrl}"><span aria-hidden="true">←</span> Voltar à página inicial</a>

    <article class="ops-login-card">
        <div class="ops-login-mark" aria-hidden="true">↗</div>
        <p class="eyebrow">Área restrita · UCSal</p>
        <h1>Painel de Operações</h1>
        <p class="ops-login-description">Acesso do atendimento da Sala de Operações para acompanhar os objetos encontrados.</p>

        <c:if test="${param.saiu == '1'}">
            <p class="ops-feedback" role="status"><span aria-hidden="true">✓</span> Sessão encerrada com segurança.</p>
        </c:if>

        <c:if test="${not empty erro}">
            <p class="ops-login-error" role="alert"><c:out value="${erro}" /></p>
        </c:if>

        <form class="ops-login-form" method="post" action="${loginAction}">
            <input type="hidden" name="csrfToken" value="<c:out value='${csrfToken}' />">
            <label for="senha">Senha de demonstração</label>
            <input id="senha" name="senha" type="password" required autocomplete="current-password">
            <button class="ops-button ops-button-primary" type="submit">Entrar no painel <span aria-hidden="true">→</span></button>
        </form>

        <p class="ops-login-note">A credencial é configurada localmente pela variável de ambiente <code>VPM_OPERACOES_SENHA</code>.</p>
    </article>
</div>

<%@ include file="rodape.jspf" %>
