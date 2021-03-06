<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>
<title>捞贝餐饮管理系统 - 增加菜谱</title>
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
	width: 33%;
	text-align: center;
}
.bottom-area{
	padding: 20px;
	border-top: 1px solid #bdbdbd;
}

</style>
</head>
<body>
<div class="well">
<div class="">
<form id="recipeForm" action="${pageContext.request.contextPath }/cookBook/addCookBook.do" method="post">
	<div class="add-content">
		<h3 class="text-center">菜名</h3>
		<div class="text-center content"  style="padding-left: 20px; padding-right: 20px;">
			<input type="text" name="name" class="form-control" placeholder="请输入菜名">
		</div>
	</div>
	<div class="add-content">
		<h3 class="text-center">主材</h3>
		<div class="text-center content">
			<table class="table" id="main-table">
				   <thead>
				      <tr>
				         <th >名称</th>
				         <th >单位</th>
				         <th >数量</th>
				      </tr>
				   </thead>
				   <tbody>
				      <tr>
				         <td>
					         <select class="form-control main-name"  onchange="onSelectStock(this);">
					         	<option disabled selected value="">请选择</option>
					         	<c:forEach items="${shicai }" var="mt">
					         		<option data-unit="${mt.unit }">${mt.name}</option>
					         	</c:forEach>
					         </select>
				         </td>
				         <td class="unit"></td>
				         <td><input type="text" class="form-control"></td>
				      </tr>
				   </tbody>
			</table>
			<button type="button" class="btn btn-default" onclick="addEntry('main-table')">增加</button>
			<input type="hidden" name="primaryMaterial" />
		</div>
	</div>
	<div class="add-content">
		<h3 class="text-center">辅材</h3>
		<div class="text-center content">
			<table class="table" id="auxiliary-table">
				   <thead>
				      <tr>
				         <th>名称</th>
				         <th>单位</th>
				         <th>数量</th>
				      </tr>
				   </thead>
				   <tbody>
				      <tr>
				         <td>
					         <select class="form-control auxiliary-name" onchange="onSelectStock(this);">
					         	<option disabled selected value="">请选择</option>
					         	<c:forEach items="${shicai }" var="mt">
					         		<option data-unit="${mt.unit }">${mt.name}</option>
					         	</c:forEach>
					         </select>
				         </td>
				         <td class="unit"></td>
				         <td><input type="text" class="form-control"></td>
				      </tr>
				   </tbody>
			</table>
			<button type="button" class="btn btn-default"  onclick="addEntry('auxiliary-table')">增加</button>
			<input type="hidden" name="auxiliaryMaterial" />
		</div>
	</div>
	<div class="add-content">
		<h3 class="text-center">调料</h3>
		<div class="text-center content">
			<table class="table" id="seasoning-table">
				   <thead>
				      <tr>
				         <th>名称</th>
				         <th>单位</th>
				         <th>数量</th>
				      </tr>
				   </thead>
				   <tbody>
				      <tr>
				         <td>
					         <select class="form-control seasoning-name" onchange="onSelectStock(this);">
					         	<option disabled selected value="">请选择</option>
					         	<c:forEach items="${tiaoliao }" var="mt">
					         		<option data-unit="${mt.unit }">${mt.name}</option>
					         	</c:forEach>
					         </select>
				         </td>
				         <td class="unit"></td>
				         <td><input type="text" class="form-control"></td>
				      </tr>
				   </tbody>
			</table>
			<button type="button" class="btn btn-default" onclick="addEntry('seasoning-table')">增加</button>
			<input type="hidden" name="seasoning" />
		</div>
	</div>
	<div class="add-content">
		<h3 class="text-center">制作方法</h3>
		<div class="content" style="padding-left: 20px; padding-right: 20px;">
			<textarea class="form-control" rows="3" name="cookingMethod"></textarea>
		</div>
	</div>
	<div class="content bottom-area add-content">
		<label for="cookingTime" >制作时间（分钟）</label>
		<select name="cookingTime" class="form-control pull-right" style="width: 50%;">
		</select>
	</div>
	<div class="content bottom-area add-content">
		<label for="cookingTime" >价格</label>
		<input type="text" name="price" class="form-control pull-right" style="width: 50%;" placeholder="请输入价格">
	</div>
	<div class="text-center" style="margin-top: 40px;">
		<button type="button" class="btn btn-primary btn-lg btn-block" onclick="save();">保存菜谱</button>
	</div>
</form>
</div>
</div>

<script type="text/javascript">
$(function(){
	var cookingTimeSelect = $(":input[name='cookingTime']");
	var timeOption = $("<option></option>");
	timeOption.text("不定");
	timeOption.val(0);
	timeOption.appendTo(cookingTimeSelect);
	for(var i=1; i <= 60; i++){
		timeOption = $("<option></option>");
		timeOption.text(i);
		timeOption.val(i);
		timeOption.appendTo(cookingTimeSelect);
	}
});

function addEntry(tableName){
	var eleModel = $("#"+tableName+" tbody tr:first").clone();
	eleModel.find("select")[0].selectedIndex=0;
	eleModel.find("input").val("");
	eleModel.find(".unit").text("");
	$("#"+tableName).append(eleModel);
}

function onSelectStock(ele){
	var $ele = $(ele);
	var selected = $ele.find("option:selected");
	var unit = selected.attr("data-unit");
	$ele.parent().nextAll(".unit").text(unit);
}

function save(){
	var canSubmit = true;
	$(".has-error").removeClass("has-error").removeAttr("data-toggle").removeAttr("data-placement").removeAttr("title").removeAttr("data-original-title");
	$(":input").tooltip('destroy');
	$(".tooltip").remove();
	
	var recipeNameInput = $(":input[name='name']");
	if(recipeNameInput.val() == ""){
		recipeNameInput.parent(".content").addClass("has-error");
		recipeNameInput.attr("placeholder", "菜名不能为空");
		recipeNameInput.attr("data-placement", "bottom");
		recipeNameInput.attr("title", "菜名不能为空");
		recipeNameInput.addClass("has-error");
		recipeNameInput.tooltip('show');
		canSubmit = false;
	}
	var priceInput = $(":input[name='price']");
	if(priceInput.val() == ""){
		priceInput.parent(".content").addClass("has-error");
		priceInput.attr("placeholder", "价格不能为空");
		priceInput.attr("data-placement", "bottom");
		priceInput.attr("title", "价格不能为空");
		priceInput.addClass("has-error");
		priceInput.tooltip('show');
		canSubmit = false;
	}
	
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
	
	
	$(":input[name='primaryMaterial']").val(getTotalstring("main-table"));
	$(":input[name='auxiliaryMaterial']").val(getTotalstring("auxiliary-table"));
	$(":input[name='seasoning']").val(getTotalstring("seasoning-table"));
	
	if(canSubmit){
		$("#recipeForm")[0].submit();
	}
}
function getTotalstring(id){
	var result = "";
	var maxIndex = $("#"+id+" tr").length - 1;
	$("#"+id+" tr").each(function(i){
		if(($(this).find("th").length != 0) || ($(this).find("option:selected:enabled").length == 0)){
			return true;
		}
		var name = $(this).find("option:selected").text();
		var count = $(this).find(":text").val();
		if(count == ""){
			result += name;
		}else{
			result += name+"*"+count;
		}
		if(i != maxIndex){
			result += ", ";
		}
	});
	
	return result;
}
</script>
</body>
</html>