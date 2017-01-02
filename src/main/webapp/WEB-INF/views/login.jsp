<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登录</title>
<link rel="stylesheet" href="${res}/css/login.css">
</head>
<body>
	<div id="frame">
        <form id="registerForm" class="fontType" action="<%= response.encodeRedirectURL("/store/user/loginCheck.do")%>" method="post">
                <div class="int" >
                    <label for="email" class="int">用户名：</label>
                    <input name="email" class="required" type="text">
                </div>
                <div class="int" >
                    <label for="password" class="int">密码：</label>
                    <input name="password" class="required" type="password">
                </div>
                <div class="sub">
                	<p id="tips"><c:out value="${message}" default=""/></p>
                    <input id="submitbtn" type="submit" value="登录" >
                </div>
                <input name="target" class="hide" type="text" value="${target}">
        </form>
    </div>
</body>
</html>