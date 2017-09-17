<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>
<title>捞贝餐饮管理系统 - 消耗与采购对比表</title>
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
	<h1 class="text-center">消耗采购对比</h1>
	<div class="mainContent">
		<form id="searchForm" action="${ctx }/chart/consumeAndPurchase.do" class="form-inline text-right" role="form">
			<div class="form-group">
				<input type="text" id="date" class="form-control" name="date"  value="${date}"
					   placeholder="日期">
				<button type="submit" class="btn btn-default">查询</button>
			</div>
		</form>
		<form id="contentForm" method="post">
			<table id="content-table" class="table table-bordered">
				<tbody>
				<c:forEach items="${typeList }" var="type">
					<tr class="type"><td colspan="9">${type }</td></tr>
					<tr class="role">
						<td colspan="4">消耗</td>
						<td colspan="4">采购</td>
						<td colspan="4">差值</td>
					</tr>
					<tr class="propName">
						<td>名称</td>
						<td>数量</td>
						<td>单价</td>
						<td>价格</td>
						<td>名称</td>
						<td>数量</td>
						<td>单价</td>
						<td>价格</td>
						<td>差值</td>
					</tr> 
					<c:forEach items="${list }" var="obj">
					<c:if test="${obj['con'].stockType==type || obj['pur'].stockType==type}">
					<tr class="content">
						<td>${obj["con"].name }</td>
						<td>${obj["con"].count }${obj["con"].unit }</td>
						<td>${obj["con"].unitPrice }</td>
						<td data-stock-type="${type }-con-price" class="one-price">${obj["con"].unitPrice * obj["con"].count}</td>
						<td>${obj["pur"].name }</td>
						<td>${obj["pur"].count }${obj["pur"].unit }</td>
						<td>${obj["pur"].unitPrice }</td>
						<td data-stock-type="${type }-pur-price"  class="one-price">${obj["pur"].unitPrice * obj["pur"].count}</td>
						<td data-stock-type="${type }-rate"  class="one-rate">${(obj["pur"].unitPrice * obj["pur"].count)/(obj["con"].unitPrice * obj["con"].count)*100}%</td>
					</tr>
					</c:if>
					</c:forEach>
					<tr>
						<td>总计</td>
						<td colspan="2"></td>
						<td data-stock-type="${type }-con-allPrice" class="all-price"></td>
						<td>总计</td>
						<td colspan="2"></td>
						<td data-stock-type="${type }-pur-allPrice" class="all-price"></td>
						<td data-stock-type="${type }-allRate" class="all-rate"></td>
					</tr>
				</c:forEach>
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