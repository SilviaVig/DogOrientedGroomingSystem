<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Edit Appointment">
    <jsp:attribute name="body">   
        <form:form action="${pageContext.request.contextPath}/appointments/edit" method="POST" modelAttribute="appointment">
            
            <c:if test="${not empty globalError }">
                <div class="alert alert-danger">
                    <c:out value="${globalError}" />
                </div>
            </c:if>
            
            <dl>
            <dt>Customer</dt>
            <dd><my:a href="/customers/view/${appointment.dog.owner.id}"> <c:out value="${appointment.dog.owner.name} ${appointment.dog.owner.surname}"/></my:a> </dd>
            
            <dt>Dog</dt>
            <dd><my:a href="/dogs/view/${appointment.dog.id}"> <c:out value="${appointment.dog.name}"/></my:a></dd>
            </dl>
            
            <form:hidden path="id" />
            <form:hidden path="dog.id" />
            <form:hidden path="dog.name" />
            <form:hidden path="dog.owner.name" />
            <form:hidden path="dog.owner.surname" />
            
            <label>Start time: 
                <div class="input">
                <form:input type="text" path="startTime" placeholder="yyyy-mm-dd hh:mm" />
                <form:errors path="startTime" cssClass="error"/>
                </div>
            </label>
            
            <label>Procedures 
                <div class="input">
                <form:select path="procedures" multiple="true"  size="${fn:length(proceduresOptions)}">
                    <form:options items="${proceduresOptions}" />
                </form:select>
                <form:errors path="procedures" cssClass="error"/>
                </div>
            </label>
                
            <label>End time: 
                <div class="input">
                <form:input type="text" path="endTime" placeholder="yyyy-mm-dd hh:mm" />
                <form:errors path="endTime" cssClass="error"/>
                </div>
            </label>
            
            <input type="submit" class="btn" value="Submit" />
            
        </form:form>
        
    </jsp:attribute>
    
</my:pagetemplate>
