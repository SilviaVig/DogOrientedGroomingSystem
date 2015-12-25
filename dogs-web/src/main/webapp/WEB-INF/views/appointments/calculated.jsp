<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New Appointment">
    <jsp:attribute name="body">   
        <form:form action="${pageContext.request.contextPath}/appointments/new" method="POST" modelAttribute="appointment">
            
            <dl>
            <dt>Customer</dt>
            <dd><my:a href="/customers/view/${appointment.dog.owner.id}"> <c:out value="${appointment.dog.owner.name} ${appointment.dog.owner.surname}"/></my:a> </dd>
            
            <dt>Dog</dt>
            <dd><my:a href="/dogs/view/${appointment.dog.id}"> <c:out value="${appointment.dog.name}"/></my:a></dd>
            </dl>
           
            <form:hidden path="dog.id" />
            
            <label>Start time: 
                <form:input type="datetime" path="startTime" placeholder="yyyy-mm-dd hh:mm" />
            </label>
            
            <label>Procedures <form:select path="procedures" multiple="true"  size="${fn:length(proceduresOptions)}">
                    <form:options items="${proceduresOptions}" />
                </form:select></label>
            
            <label>End time: 
                <form:input type="datetime" path="endTime" placeholder="yyyy-mm-dd hh:mm" />
            </label>
            
            <input type="submit" class="btn" value="Submit" />
            
        </form:form>
        
    </jsp:attribute>
    
</my:pagetemplate>
