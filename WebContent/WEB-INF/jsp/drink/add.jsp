<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>
<title>捞贝餐饮管理系统 - 增加酒水单</title>
<style type="text/css">
.well {
	margin-left: 20%;
	margin-right: 20%;
	padding-bottom: 50px;
}
.content{
	background-color: #fff;
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
.has-error{
	background-color: rgba(169, 68, 66, 0.16);
}
</style>
</head>
<body>
<div class="well">
<form id="drinkForm" action="${pageContext.request.contextPath }/drink/addDrink.do" method="post">
	<div>
		<h3 class="text-center">酒水名</h3>
		<div class="text-center content"  style="padding-left: 20px; padding-right: 20px;">
			<input type="text" name="drinkName" class="form-control" placeholder="请输入酒水名">
		</div>
	</div>
	<div>
		<h3 class="text-center">备注</h3>
		<div class="content" style="padding-left: 20px; padding-right: 20px;">
			<textarea class="form-control" rows="3" name="drinkComment"></textarea>
		</div>
	</div>
	<div class="content bottom-area">
		<label >价格</label>
		<input type="text" name="drinkPrice" class="form-control pull-right" style="width: 50%;" placeholder="请输入价格">
	</div>
	<div class="text-center" style="margin-top: 40px;">
		<button type="button" class="btn btn-primary btn-lg btn-block" onclick="save();">保存酒水</button>
	</div>
</form>
</div>

<script type="text/javascript">

function save(){
	var canSubmit = true;
	$(".has-error").removeClass("has-error");
	var recipeNameInput = $(":input[name='drinkName']");
	if(recipeNameInput.val() == ""){
		recipeNameInput.parent(".content").addClass("has-error");
		recipeNameInput.attr("placeholder", "酒水名不能为空");
		canSubmit = false;
	}
	var priceInput = $(":input[name='drinkPrice']");
	if(priceInput.val() == ""){
		priceInput.parent(".content").addClass("has-error");
		priceInput.attr("placeholder", "价格不能为空");
		canSubmit = false;
	}
	if(canSubmit){
		$("#drinkForm")[0].submit();
	}
}
</script>
</body>
</html>