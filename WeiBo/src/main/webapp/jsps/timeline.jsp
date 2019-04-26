<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
<meta content="text/html; charset=UTF-8" http-equiv="content-type">
<title>Retwis - Example Twitter clone based on the Redis Key-Value DB</title>
<link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/jquery-2.2.1.min.js"></script>
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
<h2>热点</h2>
<i>最新注册用户(redis中的sort用法)</i><br>
<br><i>最新的50条微博!</i><br>
<div><a class="username" href="profile.php?u=test">${user }</a> </div>
<c:forEach items="${hotNews }" var="webo">		
	<div class="post">
	<a class="username" href="${pageContext.request.contextPath}/guanzhu?username=${webo.userNa}">${webo.userNa }</a>
	${webo.status }<br>
	<i>${webo.time }通过 web发布</i>
	</div>
</c:forEach>


<div id="footer">redis版本的仿微博项目 <a href="http://redis.io">Redis key-value database</a></div>
</div>
</body>
</html>
