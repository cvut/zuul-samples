<%@ page contentType="application/xhtml+xml" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div id="content">
    <h1>Random movie quotation from Simple Provider:</h1>

    <h2>“<c:out value="${quote.quotation}" />”</h2>
    <p>
        by <b><c:out value="${quote.character}" /></b>
        in movie <b><c:out value="${quote.film}" /> (<c:out value="${quote.year}" />)</b>
    </p>

    <br />
    <input type="button" value="Next quote!" onclick="window.location.reload()" />
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
