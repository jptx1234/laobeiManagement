<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>
<title>捞贝餐饮管理系统 - 库存</title>
<style type="text/css">
.mainContent{
	padding: 10px;
	margin-bottom: 10px;
}
#searchForm{
	margin-bottom: 10px;
}
</style>
</head>
<body>
<div class="well">
	<h1 class="text-center">${stockType }库存</h1>
	<div class="mainContent">
		<form id="searchForm" class="form-inline text-right" role="form">
			<div class="form-group">
				<input type="text" class="form-control" name="name"  value="${name}"
					   placeholder="请输入要查询的${stockType }名">
				<input type="hidden" name="stockType" value="${stockType }" />
				<button type="submit" class="btn btn-default">查询</button>
			</div>
		</form>
		<form id="contentForm" method="post">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>选择</th>
						<th>名称</th>
						<th>数量</th>
						<th>单位</th>
						<th>单价</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="cb">
						<tr>
							<td><input type="checkbox" name="ids" value="${cb.id }"></input></td>
							<td>${cb.name }</td>
							<td>${cb.totalCount }</td>
							<td>${cb.unit}</td>
							<td>${cb.unitPrice}</td>
						</tr>
						
					</c:forEach>
				</tbody>
			</table>
		</form>
		<div class="text-right">
			<button type="button" class="btn btn-success" onclick="toAdd();">新增</button>
		</div>
	
	</div>
	<button type="button" class="btn btn-default" onclick="exportExcel(this);">导出Excel</button>

</div>
<script type="text/javascript">
	
	function toAdd(){
		window.location.href="${pageContext.request.contextPath}/stock/toAdd.do?stockType=${stockType}";
	}
	
	function exportExcel(btn){
		if(confirm("确定导出Excel表格吗")){
			var button = $(btn);
			button.text("正在生成Excel表格……");
			button.attr("disabled", "disabled");
			window.setTimeout(function(){
				button.removeAttr("disabled");
				button.text("导出Excel");
			}, 10000);
			window.location.href="${pageContext.request.contextPath}/stock/exportStock.do?stockType=${stockType}";
		}
	}
</script>
</body>
</html>