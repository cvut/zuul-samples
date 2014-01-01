<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Random movie quotation from the Quotes Provider:</h1>

<h2>“<c:out value="${quote.quotation}" />”</h2>
<p>
    by <b><c:out value="${quote.character}" /></b>
    in movie <b><c:out value="${quote.film}" /> (<c:out value="${quote.year}" />)</b>
</p>

<br />
<input type="button" value="Next quote!" onclick="window.location.reload()" />
