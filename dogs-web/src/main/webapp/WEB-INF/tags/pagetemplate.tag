<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:url value='/img/dogs-small.png' var="logoUrl" scope="page"/>
<c:url value='/css/main.css' var="cssUrl" scope="page"/>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:out value="${title}"/></title>
    
    <link rel="stylesheet" href="<c:out value='${cssUrl}'/>" />
    
    <jsp:invoke fragment="head"/>
</head>
<body>
    
<div class="container">
    <header>
        <h1><my:a href="/"><img src="<c:out value='${logoUrl}'/>" alt="DOGS" /></my:a></h1> 
        <!-- navigation bar -->
        <nav>
            <ul>
                <li><my:a href="/customers/">Customers</my:a></li>
                <li><my:a href="/dogs/">Dogs</my:a></li>
                <li><my:a href="/appointments/">Appointments</my:a></li>
                <li>
                    <c:url var="logoutUrl" value="/logout"/>
                    <form class="form-inline" action="${logoutUrl}" method="post">
                        <input type="submit" class="btn" value="Log out" />
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </li>
            </ul>
        </nav>
    </header>


    <!-- page title -->
    <c:if test="${not empty title}">
        <div class="page-header">
            <h1><c:out value="${title}"/></h1>
        </div>
    </c:if>

    <!-- authenticated user info -->
    <c:if test="${not empty authenticatedUser}">
    <div class="row">
        <c:out value="${authenticatedUser.givenName} ${authenticatedUser.surname}"/>
    </div>
    </c:if>

    <!-- alerts -->
    <c:if test="${not empty alert_danger}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <c:out value="${alert_danger}"/></div>
    </c:if>
    <c:if test="${not empty alert_info}">
        <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
    </c:if>
    <c:if test="${not empty alert_success}">
        <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
    </c:if>
    <c:if test="${not empty alert_warning}">
        <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
    </c:if>

    <!-- page body -->
    <jsp:invoke fragment="body"/>

    <!-- footer -->
    <footer class="footer">
        <p>&copy;&nbsp;<%=java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)%>&nbsp;WoofEnterprise</p>
    </footer>
</div>
</body>
</html>