<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<my:pagetemplate title="Dog #${dog.id}">
    <jsp:attribute name="body">   
        <sec:authorize access="hasRole('ADMIN')">
        <form class="inline" method="post" action="${pageContext.request.contextPath}/dogs/delete/${dog.id}">
            <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
            <my:a href="/dogs/edit/${dog.id}" class="btn">Edit dog</my:a>
            <button type="submit" class="btn">Delete dog</button>
        </form>
        </sec:authorize>
        <dl>
            <dt>Name</dt>
            <dd><c:out value="${dog.name}"/></dd>
            
            <dt>Hobbies</dt>
            <dd><c:out value="${dog.hobbies}"/></dd>
            
            <dt>Owner</dt>
            <dd><my:a href="/customers/view/${dog.owner.id}"> <c:out value="${dog.owner.name} ${dog.owner.surname}"/></my:a> </dd>
            <sec:authorize access="hasRole('ADMIN')">
            <dt>Appointments <my:a href="/appointments/new/dog/${dog.id}" class="btn">Create Appointment</my:a></dt>
            </sec:authorize>
            <dd>
                <ul>
                <c:forEach items="${dog.appointments}" var="appointment">
                    <li><my:a href="/appointments/view/${appointment.id}"><fmt:formatDate value="${appointment.startTime}" pattern="yyyy-MM-dd HH:mm" /></my:a></li> 
                </c:forEach>
                </ul>
            </dd>
        </dl>       
    </jsp:attribute>
</my:pagetemplate>