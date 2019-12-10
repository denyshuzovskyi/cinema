<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>Cinema</title>
        <link rel="shortcut icon" href="<c:url value='/images/Camera.png'/>">
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/test.css'/>" >
    </head>

    <body>
        <div id="container">
            <div id="header">
                <header>
                    <img src="<c:url value='/images/Camera.png'/>"
                         alt="Camera Logo" width="58px">
                    <h1>Cinema</h1>
                </header>

                <div id="nav_bar">
                    <ul>
                        <li><a href="<c:url value='#'/>">Test</a></li>
                        <li><a href="<c:url value='#'/>">Test</a></li>
                        <c:if test="${user.role eq 'admin'}">
                            <li><a href="<c:url value='/jsp/admin/film_upload.jsp'/>">Upload Film</a></li>
                        </c:if>
                    </ul>

                    <div id="forms">
                        <label>Language:
                            <select name="language">
                                <option value="ENG" selected>English</option>
                                <option value="UKR">Ukrainian</option>
                                <option value="RUS">Russian</option>
                            </select>
                        </label>
                        <c:choose>
                            <c:when test="${not empty user}">
                                <a href="/controller?command=logout">Logout</a>
                            </c:when>
                            <c:otherwise>
                                <a href="/jsp/login.jsp">Login</a>
                            </c:otherwise>
                        </c:choose>
                    </div> <%-- end forms --%>

                </div> <%-- end nav_bar --%>
            </div> <%-- end header --%>

    <%-- ... --%>

