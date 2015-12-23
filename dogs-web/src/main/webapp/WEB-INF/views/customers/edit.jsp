
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<my:pagetemplate title="Edit customer">
<jsp:attribute name="body">

    <form:form method="POST" action="${pageContext.request.contextPath}/customers/edit/${customer.id}"
               modelAttribute="customer" cssClass="form-horizontal">
        <form:label path="name">Name</form:label>
        <form:input path="name" value="${customer.name}"/>
        <form:label path="surname">Surname</form:label>
        <form:input path="surname"/>
        <form:label path="email" >Email</form:label>
        <form:input path="email"/>
        <form:label path="addressFirstLine">First line</form:label>
        <form:input path="addressFirstLine"/>
        <form:label path="addressSecondLine">Second line</form:label>
        <form:input path="addressSecondLine"/>
        <form:label path="addressCity">City</form:label>
        <form:input path="addressCity"/>
        <form:label path="addressCountry">Country</form:label>
        <form:input path="addressCountry"/>
        <form:label path="addressPostalCode">Postal code</form:label>
        <form:input path="addressPostalCode"/>
        <form:label path="phoneNumber">Phone Number</form:label>
        <form:input path="phoneNumber"/>

        <button class="btn btn-primary" type="submit">Edit customer</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>