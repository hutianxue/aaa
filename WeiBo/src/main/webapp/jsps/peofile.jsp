<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
<meta content="text/html; charset=UTF-8" http-equiv="content-type">
<title>Retwis - Example Twitter clone based on the Redis Key-Value DB</title>
<link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="page">
<div id="header">
<a href="/"><img style="border:none" src="${pageContext.request.contextPath}/resources/logo.png" width="192" height="85" alt="Retwis"></a>
<div id="navbar">
<a href="index.php">主页</a>
| <a href="${pageContext.request.contextPath}/hotNews">热点</a>
| <a href="${pageContext.request.contextPath}/logout">退出</a>
</div>
</div>
<h2 class="username">${uname }</h2>
<c:if test="${ifFollow==1 }">
	<a href="${pageContext.request.contextPath}/following?username=${uname}" class="button">关注ta</a>
</c:if>
<c:if test="${ifFollow==0 }">
	<a href="${pageContext.request.contextPath}/dontfollowing?username=${uname}" class="button">取消关注</a>
</c:if>

<c:forEach items="${follow }" var="webo">		
	<div class="post">
	<a class="username">${webo.userNa }</a>
	${webo.status }<br>
	<i>${webo.time }通过 web发布</i>
	</div>
</c:forEach>
<div id="footer">redis版本的仿微博项目 <a href="http://redis.io">Redis key-value database</a></div>
</div>
</body>
</html>
