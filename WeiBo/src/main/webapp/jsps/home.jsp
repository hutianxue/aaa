<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
<meta content="text/html; charset=UTF-8" http-equiv="content-type">
<title>Retwis - Example Twitter clone based on the Redis Key-Value DB</title>
<link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/jquery-2.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
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
<div id="postform">
<form method="POST" action="${pageContext.request.contextPath}/publishWb">
${user}, 有啥感想?
<br>
<table>
<tr><td><textarea cols="70" rows="3" name="status"></textarea></td></tr>
<tr><td align="right"><input type="submit" name="doit" value="Update"></td></tr>
</table>
</form>
<div id="homeinfobox">
<a data-toggle="modal" data-target="#myfans">${follower } 粉丝</a><br>
<a  data-toggle="modal" data-target="#myIdol">${mf } 关注</a><br>
</div>

<!-- 模态框（Modal）我的关注 -->
<div class="modal fade" id="myIdol" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					我的关注
				</h4>
			</div>
			<div class="modal-body">
				<script type="text/javascript">
					$(function(){
						
						$.ajax({
							url:"${pageContext.request.contextPath}/myIdol",
							type:"post",
							dataTape:"json",
							data:{},
							success:function(data){
								if(data){
									for(var i=0;i<data.length;i++){
										 $("#mdo").append("<a href='${pageContext.request.contextPath}/guanzhu?username="+data[i]+"'>"+data[i]+"</a><br>");
									}
								}
								
							}
						})
					})
				</script>
					<p id="mdo"></p>				
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
	<!-- 模态框（Modal）我的粉丝 -->
<div class="modal fade" id="myfans"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					我的粉丝
				</h4>
			</div>
			<div class="modal-body">
				<script type="text/javascript">
					$(function(){
						$.ajax({
							url:"${pageContext.request.contextPath}/myfans",
							type:"post",
							dataTape:"json",
							data:{},
							success:function(data){
								for(var i=0;i<data.length;i++){
									$("#mfan").append("<a href='${pageContext.request.contextPath}/guanzhu?username="+data[i]+"'>"+data[i]+"</a><br>");
								}
							}
						})
					})
				</script>
				<p id="mfan"></p>
				
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
</div>
<c:forEach items="${webo }" var="webo">		
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
