<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Encontrei um objeto · VoltaPraMim</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Bricolage+Grotesque:wght@500;700;800&family=Instrument+Sans:wght@400;600;700&family=JetBrains+Mono:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/cadastro.css">
</head>
<body>
<header class="registration-header">
    <div class="header-inner">
        <a class="brand" href="<c:url value='/'/>" aria-label="VoltaPraMim, página inicial"><span class="brand-icon" aria-hidden="true">←</span><span>VoltaPraMim</span></a>
        <span class="header-caption">UCSal · Achados e perdidos</span>
    </div>
</header>
<main class="registration-page">
    <a class="back-link" href="<c:url value='/'/>"><span aria-hidden="true">←</span> Voltar aos itens</a>
    <section aria-labelledby="cadastro-titulo">
        <div class="intro">
            <p class="eyebrow">Um achado. Um reencontro.</p>
            <h1 id="cadastro-titulo">Encontrei um objeto</h1>
            <p>Cada detalhe ajuda alguém a reconhecer o que perdeu.</p>
            <svg class="intro-tag" viewBox="0 0 32 32" fill="none" aria-hidden="true"><path d="M4 5h12l12 12a2 2 0 0 1 0 3L20 28a2 2 0 0 1-3 0L5 16V6a1 1 0 0 1 1-1Z" stroke="currentColor" stroke-width="1.8"/><circle cx="10" cy="10" r="1.5" fill="currentColor"/></svg>
        </div>
        <c:if test="${not empty erro}"><div class="alert" role="alert"><c:out value="${erro}" /></div></c:if>
        <form class="registration-form" action="<c:url value='/achei'/>" method="post" enctype="multipart/form-data">
            <div class="form-columns">
                <section class="object-fields" aria-labelledby="object-heading">
                    <h2 id="object-heading"><span class="section-number">01</span> Sobre o objeto</h2>
                    <div class="field">
                        <label for="titulo">Nome do objeto <span class="required" aria-hidden="true">*</span></label>
                        <input id="titulo" name="titulo" required maxlength="80" value="<c:out value='${titulo}'/>" placeholder="Ex.: garrafa térmica verde">
                    </div>
                    <div class="field">
                        <div class="field-heading"><label for="descricao">Descrição breve</label><span>Opcional</span></div>
                        <textarea id="descricao" name="descricao" rows="4" maxlength="500" aria-describedby="descricao-ajuda" placeholder="Cor, marca ou algum detalhe que ajude a identificar..."><c:out value="${descricao}"/></textarea>
                        <p class="help" id="descricao-ajuda">Não inclua nomes, números ou outros dados pessoais.</p>
                    </div>
                    <div class="field-row">
                        <div class="field">
                            <label for="categoria">Categoria</label>
                            <select id="categoria" name="categoria" required>
                                <option value="">Selecione a categoria</option>
                                <c:forEach var="categoria" items="${categorias}"><option value="${categoria.name()}"><c:out value="${categoria.rotulo}"/></option></c:forEach>
                            </select>
                        </div>
                        <div class="field">
                            <label for="local">Onde encontrou?</label>
                            <select id="local" name="local" required>
                                <option value="">Selecione o local</option>
                                <c:forEach var="local" items="${locais}"><option value="${local.name()}"><c:out value="${local.rotulo}"/></option></c:forEach>
                            </select>
                        </div>
                    </div>
                </section>
                <section class="photo-fields" aria-labelledby="photo-heading">
                    <h2 id="photo-heading"><span class="section-number">02</span> Foto do objeto</h2>
                    <div class="photo-drop" id="photo-drop">
                        <svg class="camera-icon" viewBox="0 0 32 32" fill="none" aria-hidden="true"><path d="M4 11h6l2-3h8l2 3h6v15H4V11Z" stroke="currentColor" stroke-width="2" stroke-linejoin="round"/><circle cx="16" cy="18" r="4" stroke="currentColor" stroke-width="2"/></svg>
                        <img class="photo-preview" id="foto-preview" alt="Prévia da foto selecionada" hidden>
                        <input class="photo-input" id="foto" name="foto" type="file" accept="image/jpeg,image/png" aria-describedby="foto-ajuda foto-seguranca foto-selecionada">
                        <label class="photo-button" for="foto"><span aria-hidden="true">▧</span> Adicionar foto</label>
                        <span class="drop-hint">ou arraste uma imagem aqui</span>
                        <span class="selected-file" id="foto-selecionada" aria-live="polite"></span>
                        <button class="photo-remove" id="foto-remover" type="button" hidden>Remover foto</button>
                    </div>
                    <p class="help" id="foto-ajuda">Opcional · JPEG ou PNG · Até 5 MiB</p>
                    <p class="photo-safety" id="foto-seguranca"><span aria-hidden="true">♧</span> Fotografe apenas o objeto.<br>Não envie fotos de documentos.</p>
                </section>
            </div>
            <div class="form-footer">
                <p class="notice"><span aria-hidden="true">ⓘ</span><span>O item aparecerá como <strong>Aguardando entrega.</strong> O código público identifica o registro, mas não comprova propriedade.</span></p>
                <div class="form-actions"><a href="<c:url value='/'/>">Cancelar</a><button type="submit">Cadastrar objeto <span aria-hidden="true">→</span></button></div>
            </div>
        </form>
    </section>
    <p class="page-footer">Um cuidado seu pode fazer o dia de alguém.</p>
</main>
<script src="${pageContext.request.contextPath}/static/js/cadastro.js" defer></script>
</body>
</html>
