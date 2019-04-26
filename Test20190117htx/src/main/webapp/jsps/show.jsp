<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${pageContext.request.contextPath }/static/zTree/css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/bootstrap/css/bootstrap-tab.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/common/jquery-2.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/zTree/js/jquery.ztree.all.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap/js/bootstrap-closable-tab.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap/js/closable-tab-div.js"></script>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-2 column">
			<img src="/static/images/menu/books.png" id="doc">
			<img src="/static/images/menu/trial.png" id="tri">
		</div>
		<div class="col-md-10 column">
			<div class="row clearfix">
				<div class="col-md-12 column">	
				<div style="height: 530px;overflow: auto;">		
					<ul id="myTab" class="nav nav-tabs">
			
					</ul>
					<div id="myTabContent" class="tab-content">
												
					</div>
					</div>	
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function createTab(id,name,url,isClose){
	 	closableTab.addTab({'id': id, 'name': name, 'url': url, 'closable': isClose});
	}
	$("#doc").click(function(){
		createTab("doc","文档管理","/jsps/docShow.jsp",true);
	});
	$("#tri").click(function(){
		createTab("tri","试验管理","/jsps/triShow.jsp",true);
	});
</script>
</body>
</html>