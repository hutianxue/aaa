<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html lang="it">
<head>
<meta content="text/html; charset=UTF-8" http-equiv="content-type">
<title>Redis Key-Value DB</title>
<link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/jquery-2.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	$(function(){		
		$("#addUser").submit(function(){
			var pwd1=$("#pwd1").val();
			var pwd2=$("#pwd2").val();
			if(pwd1!=pwd2){
				$("#pwdMsg").html("两次密码输入不一致");			
				return false;
			}
		})	
	})
</script>
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
<div id="welcomebox">
<div id="registerbox">
<h2>注册!</h2>
<b>想试试Retwis? 请注册账号!</b>
<form method="POST" action="${pageContext.request.contextPath}/registe" id="addUser">
<table>
<tr>
  <td>用户名</td><td><input type="text" name="username" required="required"><a style="color: red">${msg }</a></td>
</tr>
<tr>
  <td>密码</td><td><input type="password" name="password" id="pwd1" required="required"><a id="pwdMsg"></a></td>
</tr>
<tr>
  <td>密码(again)</td><td><input type="password" name="password2" id="pwd2" required="required"></td>
</tr>
<tr>
<td colspan="2" align="right"><input type="submit" name="doit" value="注册"></td></tr>
</table>
</form>
<h2>已经注册了? 请直接登陆</h2>
<form method="POST" action="${pageContext.request.contextPath}/login">
<a style="color: red">${msg2 }</a>
<table><tr>
  <td>用户名</td><td><input type="text" name="username" required="required"></td>
  </tr><tr>
  <td>密码:</td><td><input type="password" name="password" required="required"></td>
  </tr><tr>
  <td colspan="2" align="right"><input type="submit" name="doit" value="Login"></td>
</tr></table>
</form>
</div>
介绍! Retwis  是一个简单的<a href="http://twitter.com">Twitter</a>克隆, 也是<a href="http://code.google.com/p/redis/">Redis</a> key-value 数据库的一个使用安全. 关键点:
<ul>
<li>Redis 是一种key-value 数据库, 而且是本项目中 <b>唯一</b>使用的数据库, 没有用mysql等.</li>
<li>应用程序可以通过一致性哈希轻易的部署多台服务器</li>
<li>java与redis服务器的连接用jedis<a href="http://redis.io">jedis</a>
</ul>
</div>
<div id="footer">1803b-redis版本的仿微博项目 <a href="http://redis.io">Redis key-value database</a></div>
</div>
</body>
</html>
