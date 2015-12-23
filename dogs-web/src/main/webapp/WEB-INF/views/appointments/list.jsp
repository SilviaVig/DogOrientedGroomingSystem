<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:pagetemplate title="Appointments">
    <jsp:attribute name="body">   
        
        <p><my:a href="/appointments/new" class="btn" >Create new appointment</my:a></p>
        
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th width="25%">Customer</th>
                    <th width="25%">Dog</th>
                    <th width="25%">Start date</th>
                    <th width="25%">End date</th>
                    <th colspan="2">Actions</th>
                </tr>
            </thead>
            
            <tbody>
                <c:forEach items="${appointments}" var="appointment">
                    <tr>
                        <td>${appointment.id}</td>
                        <td><my:a href="/customers/view/${appointment.customer.id}">${appointment.customer.id} &mdash; <c:out value="${appointment.customer.name} ${appointment.customer.surname}"/></my:a></td>
                        <td><my:a href="/dogs/view/${appointment.dog.id}">${appointment.dog.id} &mdash; <c:out value="${appointment.dog.name}"/></my:a></td>
                        <td><fmt:formatDate value="${appointment.startTime}" pattern="yyyy-MM-dd HH:mm" /> </td>
                        <td><fmt:formatDate value="${appointment.endTime}" pattern="yyyy-MM-dd HH:mm" /> </td>
                        
                        <td>
                            <my:a href="/appointments/view/${appointment.id}" class="btn">View</my:a>
                        </td>
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/appointments/delete/${appointment.id}">
                                <button type="submit" class="btn">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
            
        </table>
        
        <p><c:out value="${fn:length(appointments)}" /> records.</p>
        
    </jsp:attribute>
</my:pagetemplate>