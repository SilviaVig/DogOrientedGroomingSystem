<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New Dog">
    <jsp:attribute name="body">   

        <form:form action="${pageContext.request.contextPath}${action}" method="POST" modelAttribute="dog">
            
            <dl>
            <dt>Customer</dt>
            <dd><my:a href="/customers/view/${dog.owner.id}"> <c:out value="${dog.owner.name} ${dog.owner.surname}"/></my:a> </dd>
            
           
            <form:hidden path="owner.id" />
            
            <label>Name 
                <form:input path="name"/>
            </label>
            
            <label>Hobbies 
                <form:input path="hobbies"/>
            </label>
            
            
            <input type="submit" class="btn" value="Submit" />
            
        </form:form>
        
    </jsp:attribute>
    
</my:pagetemplate>
