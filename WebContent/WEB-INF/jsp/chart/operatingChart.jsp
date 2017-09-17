<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>
<title>捞贝餐饮管理系统 - 经营报表</title>
<style type="text/css">
.mainContent{
	padding: 10px;
	margin-bottom: 10px;
}
#searchForm{
	margin-bottom: 10px;
}
#content-table td{
	text-align: center;
}
.type {
    background-color: #E91E63;
    color: #ffffff;
}
.role {
    background-color: #FFEB3B;
}
.propName {
    background-color: #00BCD4;
}
#content-table td, #content-table th{
	border: 1px solid #545454;
}
</style>
</head>
<body>
<div class="well">
	<h1 class="text-center">经营报表</h1>
	<div class="mainContent">
		<form id="searchForm" action="${ctx }/chart/operatingChart.do" class="form-inline text-right" role="form">
			<div class="form-group">
				<input type="text" id="date" class="form-control" name="date"  value="${date}"
					   placeholder="日期">
				<button type="submit" class="btn btn-default">查询</button>
			</div>
		</form>
		<form id="contentForm" method="post">
			<table id="content-table" class="table table-bordered">
				<tbody>
				<tr class="type"><td colspan="4">菜品</td>
				<tr class="propName">
					<td>名称</td>
					<td>单价</td>
					<td>数量</td>
					<td>总额</td>
				</tr>
				<c:forEach items="${result }" var="obj">
				<tr class="content">
					<td>${obj.name }</td>
					<td>${obj.unitPrice }</td>
					<td>${obj.count }</td>
					<td>${obj.unitPrice * obj.count }</td>
				</tr>
				</c:forEach>
				<tr class="role">
					<td>消耗总额</td>
					<td colspan="3">${consumeSum }</td>
				</tr>
				<tr class="role">
					<td>采购总额</td>
					<td colspan="3">${purchaseSum }</td>
				</tr>
				</tbody>
			</table>
		</form>

</div>
<script type="text/javascript">
$(function(){
	$(".type td").each(function(){
		var type=$(this).text();
		var conPriceSum = 0;
		var purPriceSum = 0;
		var rateSum = 0;
		$("td[data-stock-type='"+type+"-con-price']").each(function(){
			conPriceSum += parseFloat("0"+$(this).text());
		});
		$("td[data-stock-type='"+type+"-pur-price']").each(function(){
			purPriceSum += parseFloat("0"+$(this).text());
		});
		$("td[data-stock-type='"+type+"-rate']").each(function(){
			rateSum += parseFloat("0"+$(this).text().replace(/%/, ""));
		});
		$("td[data-stock-type='"+type+"-con-allPrice']").text(conPriceSum);
		$("td[data-stock-type='"+type+"-pur-allPrice']").text(purPriceSum);
		$("td[data-stock-type='"+type+"-allRate']").text(rateSum+"%");
	});
	laydate.render({
		elem: '#date',
		value: '${date}'
	});
});
</script>
</body>
</html>