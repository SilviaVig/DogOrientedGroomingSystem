<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:pagetemplate title="Appointments">
    <jsp:attribute name="head">
        <c:url value='/js/sorttable.js' var="sortableJs" scope="page"/>
        <script src="${sortableJs}"></script>
    </jsp:attribute>
        
    <jsp:attribute name="body">   
        
        <p><span class="info"> Showing <c:out value="${fn:length(appointments)}" /> records.</span>
            <sec:authorize access="hasRole('ADMIN')">
                <my:a href="/dogs/" class="btn" >Create new appointment</my:a>
            </sec:authorize>
        </p>
        
        <table class="sortable">
            <thead>
                <tr>
                    <th>Id</th>
                    <th width="25%">Customer</th>
                    <th width="25%">Dog</th>
                    <th width="25%">Start date</th>
                    <th width="25%">End date</th>
                    <th colspan="2" class="sorttable_nosort">Actions</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach items="${appointments}" var="appointment">
                    <tr>
                        <td>${appointment.id}</td>
                        <td><my:a href="/customers/view/${appointment.dog.owner.id}"><c:out value="${appointment.dog.owner.name} ${appointment.dog.owner.surname}"/></my:a></td>
                        <td><my:a href="/dogs/view/${appointment.dog.id}"><c:out value="${appointment.dog.name}"/></my:a></td>
                        <td><fmt:formatDate value="${appointment.startTime}" pattern="yyyy-MM-dd HH:mm" /> </td>
                        <td><fmt:formatDate value="${appointment.endTime}" pattern="yyyy-MM-dd HH:mm" /> </td>

                        <td>
                            <my:a href="/appointments/view/${appointment.id}" class="btn">View</my:a>
                        </td>

                        <td>
                        <sec:authorize access="hasRole('ADMIN')">
                            <form method="post" action="${pageContext.request.contextPath}/appointments/delete/${appointment.id}">
                                <button type="submit" class="btn">Delete</button>
                            </form>
                        </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>
        
        
        
    </jsp:attribute>
</my:pagetemplate>