
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<my:pagetemplate title="New customer">
<jsp:attribute name="body">

    <form:form method="POST" action="${pageContext.request.contextPath}/customers/create"
               modelAttribute="customerCreate" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${surname_error?'has-error':''}">
            <form:label path="surname" cssClass="col-sm-2 control-label">Surname</form:label>
            <div class="col-sm-10">
                <form:input path="surname" cssClass="form-control"/>
                <form:errors path="surname" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${email_error?'has-error':''}">
            <form:label path="email" cssClass="col-sm-2 control-label">Email</form:label>
            <div class="col-sm-10">
                <form:input path="email" cssClass="form-control"/>
                <form:errors path="email" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${addressFirstLine_error?'has-error':''}">
            <form:label path="addressFirstLine" cssClass="col-sm-2 control-label">First line</form:label>
            <div class="col-sm-10">
                <form:input path="addressFirstLine" cssClass="form-control"/>
                <form:errors path="addressFirstLine" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${addressSecondLine_error?'has-error':''}">
            <form:label path="addressSecondLine" cssClass="col-sm-2 control-label">Second line</form:label>
            <div class="col-sm-10">
                <form:input path="addressSecondLine" cssClass="form-control"/>
                <form:errors path="email" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${addressCity_error?'has-error':''}">
            <form:label path="addressCity" cssClass="col-sm-2 control-label">City</form:label>
            <div class="col-sm-10">
                <form:input path="addressCity" cssClass="form-control"/>
                <form:errors path="addressCity" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${addressCountry_error?'has-error':''}">
            <form:label path="addressCountry" cssClass="col-sm-2 control-label">Country</form:label>
            <div class="col-sm-10">
                <form:input path="addressCountry" cssClass="form-control"/>
                <form:errors path="addressCountry" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${addressPostalCode_error?'has-error':''}">
            <form:label path="addressPostalCode" cssClass="col-sm-2 control-label">Postal code</form:label>
            <div class="col-sm-10">
                <form:input path="addressPostalCode" cssClass="form-control"/>
                <form:errors path="addressPostalCode" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${phoneNumber_error?'has-error':''}">
            <form:label path="phoneNumber" cssClass="col-sm-2 control-label">Phone Number</form:label>
            <div class="col-sm-10">
                <form:input path="phoneNumber" cssClass="form-control"/>
                <form:errors path="phoneNumber" cssClass="help-block"/>
            </div>
        </div>
        </div>

        <button class="btn btn-primary" type="submit">Create customer</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>