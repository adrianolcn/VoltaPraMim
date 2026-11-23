<%@ page pageEncoding="UTF-8" %>
<%@ include file="cabecalho.jspf" %>
<c:url var="logoutAction" value="/operacoes/logout" />

<div class="operations-page">
    <section class="ops-heading" aria-labelledby="ops-title">
        <div>
            <p class="eyebrow">Atendimento · UCSal</p>
            <h1 id="ops-title">Painel de Operações</h1>
            <p class="ops-intro">Acompanhe os objetos recebidos e registre cada etapa do atendimento.</p>
        </div>
        <form method="post" action="${logoutAction}">
            <input type="hidden" name="csrfToken" value="<c:out value='${csrfToken}' />">
            <button class="ops-button ops-button-quiet" type="submit">Encerrar sessão</button>
        </form>
    </section>

    <c:if test="${param.sucesso == 'receber'}">
        <p class="ops-feedback" role="status"><span aria-hidden="true">✓</span> Recebimento confirmado. O item está disponível para retirada.</p>
    </c:if>
    <c:if test="${param.sucesso == 'devolver'}">
        <p class="ops-feedback" role="status"><span aria-hidden="true">✓</span> Devolução registrada. O item foi encerrado e removido das buscas públicas.</p>
    </c:if>

    <section class="ops-summary" aria-label="Resumo dos itens">
        <article class="ops-summary-card ops-summary-waiting">
            <span class="ops-summary-label">Aguardando entrega</span>
            <strong><c:out value="${painel.aguardandoEntrega.size()}" /></strong>
            <span class="ops-summary-note">Aguardam confirmação presencial</span>
        </article>
        <article class="ops-summary-card ops-summary-available">
            <span class="ops-summary-label">Disponíveis para retirada</span>
            <strong><c:out value="${painel.disponiveisParaRetirada.size()}" /></strong>
            <span class="ops-summary-note">Aguardam identificação do proprietário</span>
        </article>
        <article class="ops-summary-card ops-summary-returned">
            <span class="ops-summary-label">Devolvidos</span>
            <strong><c:out value="${painel.devolvidos.size()}" /></strong>
            <span class="ops-summary-note">Histórico desta sessão</span>
        </article>
    </section>

    <section class="ops-section" aria-labelledby="ops-waiting-title">
        <div class="ops-section-heading">
            <div>
                <p class="eyebrow">Etapa 1</p>
                <h2 id="ops-waiting-title">Aguardando entrega</h2>
            </div>
            <span class="ops-count"><c:out value="${painel.aguardandoEntrega.size()}" /> itens</span>
        </div>
        <div class="ops-list">
            <c:choose>
                <c:when test="${empty painel.aguardandoEntrega}">
                    <p class="ops-empty">Nenhum objeto aguardando entrega na sala.</p>
                </c:when>
                <c:otherwise>
                    <c:forEach var="item" items="${painel.aguardandoEntrega}">
                        <article class="ops-item">
                            <div class="ops-item-info">
                                <span class="ops-reference"><c:out value="${item.referenciaPublica}" /></span>
                                <h3><a href="<c:url value='/itens/${item.id}' />"><c:out value="${item.titulo}" /></a></h3>
                                <p><c:out value="${item.categoria.rotulo}" /> <span aria-hidden="true">·</span> <c:out value="${item.local.rotulo}" /></p>
                            </div>
                            <form method="post" action="<c:url value='/operacoes/itens/${item.id}/status' />">
                                <input type="hidden" name="csrfToken" value="<c:out value='${csrfToken}' />">
                                <input type="hidden" name="acao" value="receber">
                                <button class="ops-button ops-button-primary" type="submit">Confirmar recebimento <span aria-hidden="true">→</span></button>
                            </form>
                        </article>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </section>

    <section class="ops-section" aria-labelledby="ops-available-title">
        <div class="ops-section-heading">
            <div>
                <p class="eyebrow">Etapa 2</p>
                <h2 id="ops-available-title">Disponíveis para retirada</h2>
            </div>
            <span class="ops-count"><c:out value="${painel.disponiveisParaRetirada.size()}" /> itens</span>
        </div>
        <div class="ops-list">
            <c:choose>
                <c:when test="${empty painel.disponiveisParaRetirada}">
                    <p class="ops-empty">Nenhum objeto disponível para retirada no momento.</p>
                </c:when>
                <c:otherwise>
                    <c:forEach var="item" items="${painel.disponiveisParaRetirada}">
                        <article class="ops-item ops-item-return">
                            <div class="ops-item-info">
                                <span class="ops-reference"><c:out value="${item.referenciaPublica}" /></span>
                                <h3><a href="<c:url value='/itens/${item.id}' />"><c:out value="${item.titulo}" /></a></h3>
                                <p><c:out value="${item.categoria.rotulo}" /> <span aria-hidden="true">·</span> <c:out value="${item.local.rotulo}" /></p>
                            </div>
                            <form class="ops-return-form" method="post" action="<c:url value='/operacoes/itens/${item.id}/status' />">
                                <input type="hidden" name="csrfToken" value="<c:out value='${csrfToken}' />">
                                <input type="hidden" name="acao" value="devolver">
                                <label class="ops-confirm-check">
                                    <input type="checkbox" name="posseVerificada" value="true" required>
                                    <span>Confirmei a posse presencialmente</span>
                                </label>
                                <button class="ops-button ops-button-primary" type="submit">Registrar devolução <span aria-hidden="true">→</span></button>
                            </form>
                        </article>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </section>

    <section class="ops-section" aria-labelledby="ops-history-title">
        <div class="ops-section-heading">
            <div>
                <p class="eyebrow">Concluído</p>
                <h2 id="ops-history-title">Histórico de devoluções</h2>
            </div>
            <span class="ops-count"><c:out value="${painel.devolvidos.size()}" /> itens</span>
        </div>
        <div class="ops-list ops-history-list">
            <c:choose>
                <c:when test="${empty painel.devolvidos}">
                    <p class="ops-empty">Nenhuma devolução concluída nesta sessão.</p>
                </c:when>
                <c:otherwise>
                    <c:forEach var="item" items="${painel.devolvidos}">
                        <article class="ops-history-item">
                            <span class="ops-history-check" aria-hidden="true">✓</span>
                            <div>
                                <span class="ops-reference"><c:out value="${item.referenciaPublica}" /></span>
                                <h3><c:out value="${item.titulo}" /></h3>
                                <p><c:out value="${item.categoria.rotulo}" /> <span aria-hidden="true">·</span> <c:out value="${item.local.rotulo}" /></p>
                            </div>
                            <span class="ops-history-status">Devolvido</span>
                        </article>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </section>
</div>

<%@ include file="rodape.jspf" %>
