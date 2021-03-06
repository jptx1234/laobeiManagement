<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>
<title>捞贝餐饮管理系统 - 酒水单</title>
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
	<h1 class="text-center">酒水单</h1>
	<div class="mainContent">
		<div class="pull-left">
			<button type="button" class="btn btn-success" onclick="toAdd();">新增</button>
			<button type="button" class="btn btn-danger" onclick="deletes();">删除</button>
		</div>
		<form id="searchForm" class="form-inline text-right" role="form">
			<div class="form-group">
				<input type="text" class="form-control" name="name"  value="${name}"
					   placeholder="请输入要查询的酒水名">
				<button type="submit" class="btn btn-default">查询</button>
			</div>
		</form>
		<form id="contentForm" method="post">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>选择</th>
						<th>酒水名</th>
						<th>备注</th>
						<th>价格</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="cb">
						<tr>
							<td><input type="checkbox" name="ids" value="${cb.id }"></input></td>
							<td>${cb.drinkName }</td>
							<td>${cb.drinkComment }</td>
							<td>${cb.drinkPrice}</td>
						</tr>
						
					</c:forEach>
				</tbody>
			</table>
		</form>
		<div class="page text-right">
			<span>共${totalCount }条记录，每页${pageSize }条，当前第${currPage }页，共${pageCount }页&nbsp;</span>
			<c:if test="${currPage > 1}">
				<a href="javascript:;" onclick="toPage(${currPage - 1})">上一页</a>
			</c:if>
			<c:if test="${pageCount != 1 }">
				<span>到第
					<select onchange="toPage(this.options[this.selectedIndex].text)">
						<c:forEach begin="1" end="${pageCount }" var="pageNo">
							<option>${pageNo }</option>
						</c:forEach>
					</select>
				页</span>
			</c:if>
			<c:if test="${curPage < pageCount }">
				<a href="javascript:;" onclick="toPage(${currPage + 1})">下一页</a>
			</c:if>
		</div>
	</div>
	<button type="button" class="btn btn-default" onclick="exportExcel(this);">导出Excel</button>

</div>
<script type="text/javascript">
	function deletes(){
		var deleteCount = $("#contentForm :checkbox:checked").length;
		if (deleteCount == 0){
			alert("请勾选要删除的酒水单");
			return;
		}
		jqconfirm("确定删除吗？", function(){
			$("#contentForm").attr("action", "${pageContext.request.contextPath}/drink/deletesDrink.do");
			$("#contentForm")[0].submit();
		});
	}
	
	function toAdd(){
		window.location.href="${pageContext.request.contextPath}/drink/toAdd.do";
	}
	
	function exportExcel(btn){
		jqconfirm("确定导出Excel表格吗？", function(){
			var button = $(btn);
			button.text("正在生成Excel表格……");
			button.attr("disabled", "disabled");
			window.setTimeout(function(){
				button.removeAttr("disabled");
				button.text("导出Excel");
			}, 10000);
			window.location.href="${pageContext.request.contextPath}/drink/exportDrink.do";
		});
	}
</script>
</body>
</html>