<%@ page contentType="application/xhtml+xml" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div id="content">
    <h1>Welcome to simple OAuth 2.0 client!</h1>

    <p>This client can read a random movie quotation from Simple Provider API which is secured with OAuth 2.0 protocol.</p>

    <p><a href="<c:url value='/quote'/>">&gt;&gt;&gt; Click here &lt;&lt;&lt;</a></p>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
