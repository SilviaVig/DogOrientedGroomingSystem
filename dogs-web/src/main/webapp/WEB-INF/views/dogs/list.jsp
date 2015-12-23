<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:pagetemplate title="Dogs">
    <jsp:attribute name="body">   
        
        <p><span class="info">Showing <c:out value="${fn:length(dogs)}" /> records.</span> <my:a href="/customers/" class="btn" >Create new dog</my:a></p>
        
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th width="30%">Name</th>
                    <th width="30%">Owner</th>
                    <th width="30%" colspan="3">Actions</th>
                </tr>
            </thead>
            
            <tbody>
                <c:forEach items="${dogs}" var="dog">
                    <tr>
                        <td>${dog.id}</td>
                        <td><c:out value="${dog.name}"/></td>
                        <td><my:a href="/customers/view/${dog.owner.id}"><c:out value="${dog.owner.name} ${dog.owner.surname}"/></my:a></td>
                        <td>
                            <my:a href="/dogs/view/${dog.id}" class="btn">View</my:a>
                        </td>
                        <td>
                            <my:a href="/appointments/new/dog/${dog.id}" class="btn">Create Appointment</my:a>
                        </td>
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/dogs/delete/${dog.id}">
                                <button type="submit" class="btn">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
            
        </table>
        
        
        
    </jsp:attribute>
</my:pagetemplate>