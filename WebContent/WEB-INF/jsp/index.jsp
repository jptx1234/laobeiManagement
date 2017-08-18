<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>捞贝餐饮管理系统</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap-treeview.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap-treeview.min.js"></script>
<style type="text/css">
#tree{
	width: 15%;
}
.list-group-item{
	padding-top: 5px;
	padding-bottom: 5px;
}
.node-tree:hover{
	background-color: #dde0fb !important;
}
.title{
	width: 100%;
	margin-bottom: -35px;
}

.user-info{
	position: relative;
    text-align: right;
    bottom: 40px;
    right: 8%;
}
</style>
</head>
<body>
<div class="title">
	<img src="${pageContext.request.contextPath }/img/title.png" alt="捞贝餐饮管理系统" width="100%" />
	<div class="user-info">
		<div class="btn-group">
		<button type="button" class="btn btn-default dropdown-toggle" 
				data-toggle="dropdown">
			${user.username }<span class="caret"></span>
		</button>
		<ul class="dropdown-menu" role="menu">
			<li><a href="#">修改密码</a></li>
			<li class="divider"></li>
			<li><a href="#">退出</a></li>
		</ul>
		</div>
	</div>
</div>
<div id="tree" class="well">
</div>

<script type="text/javascript">
	$(function(){
		var treeData= [
			{
				text: '菜谱',
				state: {
					expanded: true
				},
				nodes: [
					{
						text: '菜谱',
						href: '#'
					},
					{
						text: '酒水单',
						href: '#'
					}
				],
			},
			{
				text: '库存',
				state: {
					expanded: true
				},
				nodes: [
					{
						text: '食材',
						href: '#'
					},
					{
						text: '酒水',
						href: '#'
					},
					{
						text: '调料',
						href: '#'
					},
					{
						text: '易耗品',
						href: '#'
					},
					{
						text: '固定资产',
						href: '#'
					},
				]
			},
			{
				text: '消耗列表',
				href: '#'
			},
			{
				text: '采购列表',
				href: '#'
			},
			{
				text: '报表',
				state: {
					expanded: true
				},
				nodes: [
					{
						text: '消耗与采购',
						href: '#'
					},
					{
						text: '菜品时间',
						href: '#'
					},
					{
						text: '经营报表',
						href: '#'
					},

				]
			}
			<c:if test="${user.isAdmin==1}">
			,{
				text: '系统管理',
				href: '#'
			}
			</c:if>
			];
		$("#tree").treeview({
	         color: "#428bca",
	         backColor: 'transparent',
	          showBorder: false,
	          data: treeData
	        });
		
	});
</script>

</body>
</html>