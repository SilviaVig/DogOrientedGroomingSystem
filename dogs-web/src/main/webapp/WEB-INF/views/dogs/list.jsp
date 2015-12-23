<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:pagetemplate title="Dogs">
    <jsp:attribute name="body">   
        
        <p><my:a href="/dogs/new" class="btn" >Create new dog</my:a></p>
        
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th width="50%">Name</th>
                    <th width="50%">Owner</th>
                    <th colspan="2">Actions</th>
                </tr>
            </thead>
            
            <tbody>
                <c:forEach items="${dogs}" var="dog">
                    <tr>
                        <td>${dog.id}</td>
                        <td><c:out value="${dog.name}"/></td>
                        <td><my:a href="/customers/view/${dog.owner.id}">${dog.owner.id} &mdash; <c:out value="${dog.owner.name} ${dog.owner.surname}"/></my:a></td>
                        <td>
                            <my:a href="/dogs/view/${dog.id}" class="btn">View</my:a>
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
        
        <p><c:out value="${fn:length(dogs)}" /> records.</p>
        
    </jsp:attribute>
</my:pagetemplate>