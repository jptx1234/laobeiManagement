<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>
<script type="text/javascript" src="${ctx }/plugin/html2canvas/html2canvas.min.js"></script>
<title>捞贝餐饮管理系统 - 消耗表</title>
<style type="text/css">
.mainContent{
	background: #fff;
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
.content td{
	width: 50%;
}
#content-table td, #content-table th{
	border: 1px solid #545454;
}
</style>
</head>
<body>
<div class="well center-block" style="width: 600px;">
	
	<div style="margin: 20px;">
		<button type="button" class="btn btn-primary btn-lg btn-block" onclick="saveImg();">保存图片</button>
	</div>

	<div class="mainContent" id="content">
		<table id="content-table" class="table table-bordered">
			<tbody>
			<tr class="propName" style="font-weight: bold;"><td colspan="2">消耗列表 ${date }</td></tr>
			<c:forEach items="${result }" var="type">
				<tr class="role"><td colspan="2">${type.key }</td></tr>
				<c:forEach items="${type.value }" var="item">
				<tr class="content">
					<td>${item.key}</td>
					<td>${item.value}</td>
				</tr>
				</c:forEach>
			</c:forEach>
			</tbody>
		</table>
	</div>
<script type="text/javascript">
function saveImg(){
	html2canvas($("#content")[0], {
		  onrendered: function(canvas) {
			  //var image = canvas.toDataURL("image/png").replace("image/png", "image/octet-stream");   
			  var image = canvas.toDataURL("image/png");
			  var link = $("<a></a>");
			  $("body").append(link);
			  link.attr("href", image).attr("download", "list_${date}.png");
			  link[0].click();
		  }
		});
}
</script>
</body>
</html>