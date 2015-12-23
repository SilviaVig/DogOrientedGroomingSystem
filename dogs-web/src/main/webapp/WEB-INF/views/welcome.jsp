<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:pagetemplate title="Welcome">
    <jsp:attribute name="body">   
        
        <p>Welcome to the <strong>Dog Oriented Grooming System</strong>!</p>
        
        <h2>Appointments for today</h2>
        
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th width="10%">Start time</th>
                    <th width="10%">End time</th>
                    <th width="50%">Customer</th>
                    <th width="50%">Dog</th>
                    <th>Actions</th>
                </tr>
            </thead>
            
            <tbody>
                <c:forEach items="${appointments}" var="appointment">
                    <tr>
                        <td>${appointment.id}</td>
                        <td><fmt:formatDate value="${appointment.startTime}" pattern="HH:mm" /> </td>
                        <td><fmt:formatDate value="${appointment.endTime}" pattern="HH:mm" /> </td>
                        <td><my:a href="/customers/view/${appointment.dog.owner.id}"><c:out value="${appointment.dog.owner.name} ${appointment.dog.owner.surname}"/></my:a></td>
                        <td><my:a href="/dogs/view/${appointment.dog.id}"><c:out value="${appointment.dog.name}"/></my:a></td>
                        
                        <td>
                            <my:a href="/appointments/view/${appointment.id}" class="btn">View</my:a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
            
        </table>
        
        <p><c:out value="${fn:length(appointments)}" /> appointments.</p>
        
    </jsp:attribute>
</my:pagetemplate>