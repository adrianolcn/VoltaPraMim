<%@ page pageEncoding="UTF-8" %>
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

<section class="design-section" aria-labelledby="upload-title">
    <p class="eyebrow">Componente</p>
    <h2 id="upload-title">Upload de foto</h2>
    <p>
        O campo aceita imagem opcional para itens comuns.
        Documentos não devem receber foto.
    </p>

    <div class="design-examples">
        <div class="design-example">
            <h3>Estado padrão</h3>

            <div class="field">
                <label for="foto-exemplo">
                    Foto do objeto
                    <span class="muted">(opcional)</span>
                </label>

                <input
                        id="foto-exemplo"
                        class="file-input"
                        name="foto"
                        type="file"
                        accept="image/jpeg,image/png"
                        aria-describedby="foto-ajuda">

                <p id="foto-ajuda" class="form-note">
                    JPEG ou PNG de até 5 MiB.
                </p>
            </div>
        </div>

        <div class="design-example">
            <h3>Estado de erro</h3>

            <div class="field field-error">
                <label for="foto-invalida">
                    Foto do objeto
                    <span class="muted">(opcional)</span>
                </label>

                <input
                        id="foto-invalida"
                        class="file-input"
                        name="foto"
                        type="file"
                        aria-invalid="true"
                        aria-describedby="foto-erro">

                <p id="foto-erro" class="field-message" role="alert">
                    Envie somente uma imagem JPEG ou PNG de até 5 MiB.
                </p>
            </div>
        </div>
    </div>
</section>

<%@ include file="rodape.jspf" %>
