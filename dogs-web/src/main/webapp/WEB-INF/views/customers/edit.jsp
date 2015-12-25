
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
        
        <label>Name 
            <div class="input">
                <form:input path="name" />
                <form:errors cssClass="error"  path="name"/>
            </div>
        </label> 

        <label>Surname 
            <div class="input">
                <form:input path="surname" />
                <form:errors cssClass="error"  path="surname"/>
            </div>
        </label> 

        <label>Email 
            <div class="input">
                <form:input path="email" />
                <form:errors cssClass="error"  path="email"/>
            </div>
        </label> 

        <label>First line 
            <div class="input">
                <form:input path="addressFirstLine" />
                <form:errors cssClass="error"  path="addressFirstLine"/>
            </div>
        </label> 

        <label>Second line 
            <div class="input">
                <form:input path="addressSecondLine" />
                <form:errors cssClass="error"  path="addressSecondLine"/>
            </div>
        </label> 

        <label>City 
            <div class="input">
                <form:input path="addressCity" />
                <form:errors cssClass="error"  path="addressCity"/>
            </div>
        </label> 

        <label>Country 
            <div class="input">
                <form:input path="addressCountry" />
                <form:errors cssClass="error"  path="addressCountry"/>
            </div>
        </label> 

        <label>Postal code 
            <div class="input">
                <form:input path="addressPostalCode" />
                <form:errors cssClass="error"  path="addressPostalCode"/>
            </div>
        </label> 

        <label>Phone Number 
            <div class="input">
                <form:input path="phoneNumber" />
                <form:errors cssClass="error"  path="phoneNumber"/>
            </div>
        </label> 

        <button class="btn btn-primary" type="submit">Edit customer</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>