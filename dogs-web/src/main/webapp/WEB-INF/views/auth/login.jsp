<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" />
<html>
<head>
    <title>Login Page</title>
    <style>
        body {
            font: 14px/1.2 "Segoe UI", sans-serif;
        }



        .container {
            margin: 100px auto;
            width: 350px;
        }

        .logo, .footer {
            text-align: center;
            color: #ccc;
        }


        #login-box {
            margin: 30px 0 10px;
            padding: 10px 20px;
            background: #fff;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .msg {
            border: 1px solid #ddd;
            font: 14px/1.2 "Segoe UI", sans-serif;
            padding: 10px;
            border-radius: 3px;
            color: #6E45AC;
            background-color: #dcd1ec;
            border-color: #c0acde;
            margin: 10px 0;
        }

        .error {
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }

        input {
            width: 100%;
            border: 1px solid #ddd;
            background: #fafafa;
            font: 14px/1.2 "Segoe UI", sans-serif;
            padding: 5px;
            border-radius: 3px;
            color: #333;
        }

        input.button {
            background: #8963c1;
            /* Old browsers */
            background: -moz-linear-gradient(top, #8963c1 0%, #6E45AC 100%);
            /* FF3.6+ */
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #8963c1), color-stop(100%, #6E45AC));
            /* Chrome,Safari4+ */
            background: -webkit-linear-gradient(top, #8963c1 0%, #6E45AC 100%);
            /* Chrome10+,Safari5.1+ */
            background: -o-linear-gradient(top, #8963c1 0%, #6E45AC 100%);
            /* Opera 11.10+ */
            background: -ms-linear-gradient(top, #8963c1 0%, #6E45AC 100%);
            /* IE10+ */
            background: linear-gradient(to bottom, #8963c1 0%, #6E45AC 100%);
            /* W3C */
            filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff', endColorstr='#000000',GradientType=0 );
            /* IE6-9 */
            color: #fff;
            cursor: pointer;
            text-decoration: none;
            white-space: nowrap;
            vertical-align: baseline;
            padding: 10px;
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #666;
        }

    </style>
</head>
<body onload='document.loginForm.username.focus();'>


<div class="container">
    <div class="logo">
        <img src="img/dogs-small.png">
    </div>
    <div id="login-box">
        <h2>Sign in</h2>


        <c:if test="${param.error ne null}">
            <div class="msg error">Bad credentials.</div>
        </c:if>
        <c:if test="${param.logout ne null}">
            <div class="msg">You have been logged out.</div>
        </c:if>


        <form name='loginForm' method='POST'>
             <label>
                    User:
                    <input type='text' name='username' value=''></label>

            <label>
                    Password:
                    <input type='password' name='password' /></label>

               <input class="button" name="submit" type="submit"
                                           value="Sign in" />
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />

        </form>

    </div>
    <div class="footer">
        <fmt:formatDate value="${date}" pattern="yyyy" />, Woof Enterprise
    </div>
</div>

</body>
</html>