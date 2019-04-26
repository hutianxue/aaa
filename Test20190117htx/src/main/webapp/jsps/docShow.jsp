<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	var setting = {
			async: {
				enable: true,
				url: "${pageContext.request.contextPath }/docShow",
				dataType: "json"
			},
			data: {
				simpleData: {
					//使用 / 不使用 简单数据模式
					enable: true,//如果设置为 true，请务必设置 setting.data.simpleData 内的其他参数: idKey / pIdKey / rootPId，并且让数据满足父子关系。
					idKey: "id",
					pIdKey: "parentId",
					rootPId: 0
				}
			}
	}
	//初始化ztree
	$(document).ready(function(){
		$.fn.zTree.init($("#docShow"), setting);
	});
</script>
</head>
<body>
<div class="tab-pane" id="doc">
	<div id="docShow" class="ztree"></div>
</div>
</body>
</html>