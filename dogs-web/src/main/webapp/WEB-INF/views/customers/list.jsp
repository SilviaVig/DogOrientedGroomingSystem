<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:pagetemplate title="Customers">
    <jsp:attribute name="head">
        <c:url value='/js/sorttable.js' var="sortableJs" scope="page"/>
        <script src="${sortableJs}"></script>
    </jsp:attribute>
        
    <jsp:attribute name="body">

        <p><span class="info">Showing <c:out value="${fn:length(customers)}" /> records.</span>
            <my:a href="/customers/create" class="btn">Create new customer</my:a>
        </p>

        <table class="sortable">
            <thead>
            <tr>
                <th>Id</th>
                <th width="50%">Name and surname</th>
                <th width="50%">email</th>
                <th colspan="3" class="sorttable_nosort">Actions</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${customers}" var="customer">
                <tr>
                    <td>${customer.id}</td>
                    <td><c:out value="${customer.name} ${customer.surname}"/></td>
                    <td><c:out value="${customer.email}"/></td>
                    <td>
                        <my:a href="/customers/view/${customer.id}" class="btn">View</my:a>
                    </td>
                    <td>
                        <my:a href="/dogs/new/customer/${customer.id}" class="btn">Create Dog</my:a>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/customers/delete/${customer.id}">
                            <button type="submit" class="btn">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:pagetemplate>