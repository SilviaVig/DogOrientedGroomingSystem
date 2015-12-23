<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<my:pagetemplate title="Customer #${customer.id}">
    <jsp:attribute name="body">   
        
        <form class="inline" method="post" action="${pageContext.request.contextPath}/customers/delete/${customer.id}">
            <my:a href="/customers/edit/${customer.id}" class="btn">Edit customer</my:a>
            <button type="submit" class="btn">Delete customer</button>
        </form>
        
        <dl>
            <dt>Name</dt>
            <dd><c:out value="${customer.name} ${customer.surname}"/></dd>

            <dt>Email</dt>
            <dd><c:out value="${customer.email}"/></dd>

            <dt>Adress</dt>
            <dd><c:out
                    value="${customer.addressFirstLine},
                    ${customer.addressSecondLine == null ? customer.addressSecondLine : customer.addressSecondLine.concat(\",\")}
                    ${customer.addressCity},
                    ${customer.addressCountry},
                    ${customer.addressPostalCode}"/>
            </dd>
            <dt>Phone</dt>
            <dd><c:out value="${customer.phoneNumber}"/></dd>
            <dt>Dogs <my:a href="/dogs/new/customer/${customer.id}" class="btn">Create Dog</my:a></dt>
            <dd>
                <ul>
                <c:forEach items="${customer.dogs}" var="dog">
                    <li><my:a href="/dogs/view/${dog.id}"><c:out value="${dog.name}"/></my:a></li>
                </c:forEach>
                </ul>
            </dd>
        </dl>       
    </jsp:attribute>
</my:pagetemplate>