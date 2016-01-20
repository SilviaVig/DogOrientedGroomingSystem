<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<my:pagetemplate title="Appointment #${appointment.id}">
    <jsp:attribute name="body">   
            <sec:authorize access="hasRole('ADMIN')">
        <form class="inline" method="post" action="${pageContext.request.contextPath}/appointments/delete/${appointment.id}">
            <my:a href="/appointments/edit/${appointment.id}" class="btn">Edit appointment</my:a>
            <button type="submit" class="btn">Delete appointment</button>
        </form>
        </sec:authorize>
        <dl>
            <dt>Start date</dt>
            <dd><fmt:formatDate value="${appointment.startTime}" pattern="yyyy-MM-dd HH:mm" /> </dd>
            
            <dt>End date</dt>
            <dd><fmt:formatDate value="${appointment.endTime}" pattern="yyyy-MM-dd HH:mm" /> </dd>
            
            <dt>Customer</dt>
            <dd><my:a href="/customers/view/${appointment.dog.owner.id}"> <c:out value="${appointment.dog.owner.name} ${appointment.dog.owner.surname}"/></my:a> </dd>
            
            <dt>Dog</dt>
            <dd><my:a href="/dogs/view/${appointment.dog.id}"> <c:out value="${appointment.dog.name}"/></my:a></dd>
            
            <dt>Procedures</dt>
                <dd><ul class="procedures">
                
                <c:forEach items="${appointment.procedures}" var="procedure">
                    <li><c:out value="${fn:toLowerCase(procedure)}" /></li>
                </c:forEach>            
                            
                </ul></dd>
            
        </dl>       
    </jsp:attribute>
</my:pagetemplate>