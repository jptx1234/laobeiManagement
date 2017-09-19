<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>
<title>捞贝餐饮管理系统 - 采购列表</title>
<style type="text/css">
.mainContent{
	padding: 10px;
	margin-bottom: 10px;
}
#searchForm{
	margin-bottom: 10px;
}
th, td{
	text-align: center;
}
.date-td, .op-td{
	width: 10%;
}
.content-td{
	width: 80%;
}
</style>
</head>
<body>
<div class="well">
	<h1 class="text-center">采购列表</h1>
	<div class="mainContent">
		<div class="pull-left">
			<button type="button" class="btn btn-success" onclick="toAdd();">新增</button>
		</div>
		<form id="searchForm" action="${ctx }/purchase/listAll.do" class="form-inline text-right" role="form">
			<div class="form-group">
				开始日期：<input type="text" id="beginDate" class="form-control" name="beginDate"  value="${beginDate}"
					   placeholder="起始日期">
				&nbsp;结束日期：<input type="text" id="endDate" class="form-control" name="endDate"  value="${endDate}"
					   placeholder="结束日期">
				<button type="submit" class="btn btn-default">查询</button>
			</div>
		</form>
		<form id="contentForm" method="post">
			<table class="table table-hover">
				<thead>
					<tr>
						<th class="date-td">日期</th>
						<th class="content-td">摘要</th>
						<th class="op-td">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="cb">
						<tr>
							<td class="date-td">${cb["date"] }</td>
							<td class="content-td">${cb["content"] }</td>
							<td class="op-td">
								<button type="button" class="btn btn-info" onclick='getPic("${cb["date"]}")'>保存图片</button>
							</td>
						</tr>
						
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>

</div>
<script type="text/javascript">
	$(function(){
		laydate.render({
			elem: '#beginDate',
			value: '${beginDate}'
		});
		laydate.render({
			elem: '#endDate',
			value: '${endDate}'
		});
	});
	
	function toAdd(){
		window.location.href="${pageContext.request.contextPath}/purchase/toAdd.do";
	}
	
	function getPic(date){
		window.open("${ctx}/purchase/generatePurchaseList.do?date="+date, "_blank");
	}
	
</script>
</body>
</html>