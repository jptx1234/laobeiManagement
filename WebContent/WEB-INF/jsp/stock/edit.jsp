<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>
<title>捞贝餐饮管理系统 - 编辑库存</title>
<style type="text/css">
.well {
	margin-left: 20%;
	margin-right: 20%;
	padding-bottom: 50px;
}
.content{
	padding-bottom: 20px;
    padding-top: 20px;
}
.table td, .table th{
	width: 50%;
	text-align: center;
}
.bottom-area{
	padding: 20px;
	border-top: 1px solid #bdbdbd;
}
.form-control{
	width: 50%;
}
</style>
</head>
<body>
<c:if test="${entity==null}">
	<c:set var="isNew" value="true"></c:set>
</c:if>
<c:if test="${entity!=null}">
	<c:set var="isNew" value="false"></c:set>
</c:if>
<div class="well">
<div class="">
<form id="drinkForm" 
	<c:if test="${isNew }">
		action="${pageContext.request.contextPath }/stock/addStock.do"
	</c:if> 
	<c:if test="${!isNew }">
		action="${pageContext.request.contextPath }/stock/updateStock.do"
	</c:if> 
	method="post">
	<div>
		<h2 class="text-center" style="color: #fff;">
			<c:if test="${isNew}">
				新增库存
			</c:if>
			<c:if test="${!isNew}">
				修改库存
				<input type="hidden" name="id" value="${entity.id}" />
			</c:if>
		</h2>
	</div>
	<div class="content bottom-area add-content">
		<label >名称</label>
		<input type="text" name="name" value="${entity.name }" class="form-control pull-right" <c:if test="${!isNew }">disabled</c:if> placeholder="请输入名称">
	</div>
	<div class="content bottom-area add-content">
		<label >单位</label>
		<input type="text" name="unit" value="${entity.unit }" class="form-control pull-right" placeholder="请输入单位">
	</div>
	<div class="content bottom-area add-content">
		<label >单价</label>
		<input type="text" name="unitPrice" value="${entity.unitPrice }"  class="form-control pull-right" placeholder="请输入单价">
	</div>
	<div class="content bottom-area add-content">
		<label >数量</label>
		<input type="text" name="totalCount" value="${entity.totalCount }"  class="form-control pull-right" placeholder="请输入数量">
	</div>
	<div class="content bottom-area add-content">
		<label >类型（不可修改）</label>
		<input type="text" name="stockType" 
			value="<c:if test="${isNew }">${stockType }</c:if><c:if test="${!isNew }">${entity.stockType }</c:if>" 
			class="form-control pull-right" readonly/>
	</div>
	<div class="text-center" style="margin-top: 40px;">
		<button type="button" class="btn btn-primary btn-lg btn-block" onclick="save();">保存库存</button>
	</div>
</form>
</div>
</div>

<script type="text/javascript">

function checkNotEmpty(ele, msg){
	if(ele.val() == null || ele.val() == ""){
		ele.parent(".content").addClass("has-error");
		ele.attr("placeholder", msg);
		ele.attr("data-placement", "bottom");
		ele.attr("title", msg);
		ele.addClass("has-error");
		ele.tooltip('show');
		return false;
	}
	
	return true;
}

function save(){
	var canSubmit = true;
	$(".has-error").removeClass("has-error").removeAttr("data-toggle").removeAttr("data-placement").removeAttr("title").removeAttr("data-original-title");
	$(":text").tooltip('destroy');
	$(".tooltip").remove();
	
	canSubmit = canSubmit & checkNotEmpty($(":input[name='name']"), "库存名不能为空");
	canSubmit = canSubmit & checkNotEmpty($(":input[name='unit']"), "单位不能为空");
	canSubmit = canSubmit & checkNotEmpty($(":input[name='unitPrice']"), "单价不能为空");
	canSubmit = canSubmit & checkNotEmpty($(":input[name='totalCount']"), "数量不能为空");
	
	
	$(":input").each(function(){
		var $this = $(this);
		var v = $this.val();
		if(v != null && (v.indexOf(",") >= 0 || v.indexOf("*") >= 0)){
			$this.attr("data-placement", "bottom");
			$this.attr("title", "不能有逗号（,）星号（*）");
			$this.addClass("has-error");
			$this.tooltip('show');
			canSubmit = false;
		}
	});
	
	
	if(canSubmit){
		$("#drinkForm")[0].submit();
	}
}
</script>
</body>
</html>