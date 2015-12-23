<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:choose>
    <c:when test="${empty appointmentsCreate.startTime}" >
        <c:set var="title" value="New appointment" />
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Edit appointment" />
    </c:otherwise>
</c:choose>

<my:pagetemplate title="${title}">
    <jsp:attribute name="body">   

        <form:form action="${pageContext.request.contextPath}${action}" method="POST" modelAttribute="appointmentCreate">
            
            <dl>
            <dt>Customer</dt>
            <dd><my:a href="/customers/view/${dog.owner.id}"> <c:out value="${dog.owner.name} ${dog.owner.surname}"/></my:a> </dd>
            
            <dt>Dog</dt>
            <dd><my:a href="/dogs/view/${dog.id}"> <c:out value="${dog.name}"/></my:a></dd>
            </dl>
           
            <form:hidden path="dogId" />
            
            <label>Time: 
                <form:input type="datetime" path="startTime" placeholder="yyyy-mm-dd hh:mm" />
                <form:input type="datetime" path="endTime" placeholder="yyyy-mm-dd hh:mm" />
            </label>
            
            <label>Procedures <form:select path="procedures" multiple="true"  size="${fn:length(proceduresOptions)}">
                    <form:options items="${proceduresOptions}" />
                </form:select></label>
            
            <input type="submit" class="btn" value="Submit" />
            
        </form:form>
        
    </jsp:attribute>
    
</my:pagetemplate>
