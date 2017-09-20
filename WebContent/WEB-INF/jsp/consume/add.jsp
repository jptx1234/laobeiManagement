<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>
<title>捞贝餐饮管理系统 - 消耗列表录入</title>
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
	width: 20%;
	text-align: center;
}
.bottom-area{
	padding: 20px;
	border-top: 1px solid #bdbdbd;
}
.add-tip{
	box-shadow: 0 0 20px #2dc0ec;
}
td{
	vertical-align: middle;
}
</style>
</head>
<body>
<div class="well">
<div class="">
<div class="pull-left add-content" style="position: fixed;right: 0;top: 0;padding: 20px;width: 16%;margin-right: 2%;height: 100%;">
	<select id="typeSelect" class="form-control" onchange="onSelectType();">
		<option id="typeSelectLoading" disabled selected>加载中……</option>
		<option value="cp">菜品</option>
		<option value="js">酒水</option>
		<option value="sc">食材</option>
		<option value="tl">调料</option> 
		<option value="yhp">易耗品</option>
		<option value="gdzc">固定资产</option>
		<option value="gz">工资</option>
		<option value="glfy">管理费用</option>
		<option value="ywzc">意外支出</option>
	</select>
	<select id="itemSelect" class="form-control" size=10 style="
    height: 80%;
    margin-top: 10px;
	">
	</select>
</div>
<form id="recipeForm" action="${pageContext.request.contextPath }/consume/addConsume.do" method="post">
	<div  class="add-content">
		<h3 class="text-center">菜品</h3>
		<div class="text-center content">
			<table class="table table-hover table-striped" id="cp-table">
				   <thead>
				      <tr>
				         <th >名称</th>
				         <th >单位</th>
				         <th >单价</th>
				         <th >数量</th>
				         <th >删除</th>
				      </tr>
				   </thead>
				   <tbody>
				      <tr class="hidden">
				         <td>
				         </td>
				         <td>
				         </td>
				         <td>
				         </td>
				         <td>
				         <input type="text" class="form-control">
				         	
				         </td>
				         <td>
				         	<button type="button" class="btn btn-danger" onclick="deleteRow(this);"><span class="glyphicon glyphicon-trash"></span></button>
				         </td>
				      </tr>
				   </tbody>
			</table>
			<input type="hidden" name="cp" />
		</div>
	</div>
	<div class="add-content">
		<h3 class="text-center">酒水</h3>
		<div class="text-center content">
			<table class="table" id="js-table">
				   <thead>
				      <tr>
				         <th >名称</th>
				         <th >单位</th>
				         <th >单价</th>
				         <th >数量</th>
				         <th >删除</th>
				      </tr>
				   </thead>
				   <tbody>
				      <tr class="hidden">
				         <td>
				         </td>
				         <td>
				         </td>
				         <td>
				         </td>
				         <td><input type="text" class="form-control"></td>
				         <td>
				         	<button type="button" class="btn btn-danger" onclick="deleteRow(this);"><span class="glyphicon glyphicon-trash"></span></button>
				         </td>
				      </tr>
				   </tbody>
			</table>
			<input type="hidden" name="js" />
		</div>
	</div>
	<div class="add-content">
		<h3 class="text-center">食材</h3>
		<div class="text-center content">
			<table class="table" id="sc-table">
				   <thead>
				      <tr>
				         <th >名称</th>
				         <th >单位</th>
				         <th >单价</th>
				         <th >数量</th>
				         <th >删除</th>
				      </tr>
				   </thead>
				   <tbody>
				      <tr class="hidden">
				         <td>
				         </td>
				         <td>
				         </td>
				         <td>
				         </td>
				         <td><input type="text" class="form-control"></td>
				         <td>
				         	<button type="button" class="btn btn-danger" onclick="deleteRow(this);"><span class="glyphicon glyphicon-trash"></span></button>
				         </td>
				      </tr>
				   </tbody>
			</table>
			<input type="hidden" name="sc" />
		</div>
	</div>
	<div class="add-content">
		<h3 class="text-center">调料</h3>
		<div class="text-center content">
			<table class="table" id="tl-table">
				   <thead>
				      <tr>
				         <th >名称</th>
				         <th >单位</th>
				         <th >单价</th>
				         <th >数量</th>
				         <th >删除</th>
				      </tr>
				   </thead>
				   <tbody>
				      <tr class="hidden">
				         <td>
				         </td>
				         <td>
				         </td>
				         <td>
				         </td>
				         <td><input type="text" class="form-control"></td>
				         <td>
				         	<button type="button" class="btn btn-danger" onclick="deleteRow(this);"><span class="glyphicon glyphicon-trash"></span></button>
				         </td>
				      </tr>
				   </tbody>
			</table>
			<input type="hidden" name="tl" />
		</div>
	</div>
	<div class="add-content">
		<h3 class="text-center">易耗品</h3>
		<div class="text-center content">
			<table class="table" id="yhp-table">
				   <thead>
				      <tr>
				         <th >名称</th>
				         <th >单位</th>
				         <th >单价</th>
				         <th >数量</th>
				         <th >删除</th>
				      </tr>
				   </thead>
				   <tbody>
				      <tr class="hidden">
				         <td>
				         </td>
				         <td>
				         </td>
				         <td>
				         </td>
				         <td><input type="text" class="form-control"></td>
				         <td>
				         	<button type="button" class="btn btn-danger" onclick="deleteRow(this);"><span class="glyphicon glyphicon-trash"></span></button>
				         </td>
				      </tr>
				   </tbody>
			</table>
			<input type="hidden" name="yhp" />
		</div>
	</div>
	<div class="add-content">
		<h3 class="text-center">固定资产</h3>
		<div class="text-center content">
			<table class="table" id="gdzc-table">
				   <thead>
				      <tr>
				         <th >名称</th>
				         <th >单位</th>
				         <th >单价</th>
				         <th >数量</th>
				         <th >删除</th>
				      </tr>
				   </thead>
				   <tbody>
				      <tr class="hidden">
				         <td>
				         </td>
				         <td>
				         </td>
				         <td>
				         </td>
				         <td><input type="text" class="form-control"></td>
				         <td>
				         	<button type="button" class="btn btn-danger" onclick="deleteRow(this);"><span class="glyphicon glyphicon-trash"></span></button>
				         </td>
				      </tr>
				   </tbody>
			</table>
			<input type="hidden" name="gdzc" />
		</div>
	</div>
	<div class="add-content">
		<h3 class="text-center">工资</h3>
		<div class="text-center content">
			<table class="table" id="gz-table">
				   <thead>
				      <tr>
				         <th >名称</th>
				         <th >单位</th>
				         <th >单价</th>
				         <th >数量</th>
				         <th >删除</th>
				      </tr>
				   </thead>
				   <tbody>
				      <tr class="hidden">
				         <td>
				         </td>
				         <td>
				         </td>
				         <td>
				         </td>
				         <td><input type="text" class="form-control"></td>
				         <td>
				         	<button type="button" class="btn btn-danger" onclick="deleteRow(this);"><span class="glyphicon glyphicon-trash"></span></button>
				         </td>
				      </tr>
				   </tbody>
			</table>
			<input type="hidden" name="gz" />
		</div>
	</div>
	<div class="add-content">
		<h3 class="text-center">管理费用</h3>
		<div class="text-center content">
			<table class="table" id="glfy-table">
				   <thead>
				      <tr>
				         <th >名称</th>
				         <th >单位</th>
				         <th >单价</th>
				         <th >数量</th>
				         <th >删除</th>
				      </tr>
				   </thead>
				   <tbody>
				      <tr class="hidden">
				         <td>
				         </td>
				         <td>
				         </td>
				         <td>
				         </td>
				         <td><input type="text" class="form-control"></td>
				         <td>
				         	<button type="button" class="btn btn-danger" onclick="deleteRow(this);"><span class="glyphicon glyphicon-trash"></span></button>
				         </td>
				      </tr>
				   </tbody>
			</table>
			<input type="hidden" name="glfy" />
		</div>
	</div>
	<div class="add-content">
		<h3 class="text-center">意外支出</h3>
		<div class="text-center content">
			<table class="table" id="ywzc-table">
				   <thead>
				      <tr>
				         <th >名称</th>
				         <th >单位</th>
				         <th >单价</th>
				         <th >数量</th>
				         <th >删除</th>
				      </tr>
				   </thead>
				   <tbody>
				      <tr class="hidden">
				         <td>
				         </td>
				         <td>
				         </td>
				         <td>
				         </td>
				         <td><input type="text" class="form-control"></td>
				         <td>
				         	<button type="button" class="btn btn-danger" onclick="deleteRow(this);"><span class="glyphicon glyphicon-trash"></span></button>
				         </td>
				      </tr>
				   </tbody>
			</table>
			<input type="hidden" name="ywzc" />
		</div>
	</div>
	
	
	<div class="text-center" style="margin-top: 40px;">
		<button type="button" id="saveBtn" class="btn btn-primary btn-lg btn-block" onclick="save()">保存消耗列表</button>
	</div>
</form>
</div>
</div>

<script type="text/javascript">
var stockData = null;

$(function(){
	$.get("${ctx}/consume/listAllStockJSON.do", {}, function(data){
		stockData = data;
		$("#typeSelectLoading").text("请选择种类");
		$("#itemSelect").dblclick(onSelectItem);
	}, "json");
});

function onSelectType(){
	var selectVal = $("#typeSelect").val();
	var itemSelect = $("#itemSelect");
	itemSelect.html("");
	var items = stockData[selectVal];
	for(var i=0; i < items.length; i++){
		var item = $('<option id="'+items[i]['id']+'" >'+items[i]['name']+'</option>');
		itemSelect.append(item);
	}
}

function onSelectItem(){
	var type = $("#typeSelect").val();
	var item = $("#itemSelect option:selected").text();
	if(item == ""){
		return;
	}
	var itemId = $("#itemSelect option:selected").attr("id");
	var type = $("#typeSelect").val();
	var tb = $("#"+type+"-table tbody");
	var eleModel = tb.find("tr:first").clone();
	eleModel.attr("id", type+"-item-"+itemId)
	var tds = eleModel.find("td");
	$(tds[0]).text(item);
	var itemsOfType = stockData[type];
	for(var i = 0; i < itemsOfType.length; i++){
		if(itemsOfType[i]["id"]==itemId){
			if(itemsOfType[i].hasOwnProperty("unit")){
				$(tds[1]).text(itemsOfType[i]["unit"]);
			}else{
				$(tds[1]).text("份");
			}
			if(itemsOfType[i].hasOwnProperty("unitPrice")){
				$(tds[2]).text(itemsOfType[i]["unitPrice"]);
			}
			itemsOfType.splice(i, 1);//提取完属性之后，把这个项从stockData中删除，让他不会再出现在选择框中
			break;
		}
	}
	eleModel.removeClass("hidden");
	tb.append(eleModel);
	$(".add-tip").removeClass("add-tip");
	eleModel.addClass("add-tip");
	window.location.hash = ("#"+eleModel.attr("id"));
	onSelectType();
	eleModel.find(":text").focus();
	
}

function deleteRow(btn){
	var aim = $(btn).closest("tr");
	aim.fadeOut("fast", function(){
		
		aim.remove();
	});
}


function save(){
	var canSubmit = true;
	$(".has-error").removeClass("has-error").removeAttr("data-toggle").removeAttr("data-placement").removeAttr("title").removeAttr("data-original-title");
	$("tr").tooltip('destroy');
	$(".tooltip").remove();
	var patrn = /^\d+(\.\d+)?$/;
	$("tr:visible :text").each(function(){
		if (!patrn.exec(this.value)) {
			console.log("err:"+this.value)
			var $this = $(this).closest("tr:visible");
			/* $this.attr("data-toggle", "tooltip"); */
			$this.attr("data-placement", "bottom");
			$this.attr("title", "必须输入数字");
			$this.addClass("has-error");
			$this.tooltip('show');
			canSubmit = false;
		}
	});
	
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
	
	for (var type in stockData){
		$(":input[name='"+type+"']").val(getTotalString(type+"-table"));
	}
	
	if(canSubmit){
		$("#saveBtn").attr("disabled", "disabled");
		$("#saveBtn").text("正在保存并更新库存，请耐心等候");
		$("#recipeForm")[0].submit();
	}
}
function getTotalString(id){
	var result = "";
	var trs = $("#"+id+" tbody tr:visible");
	var maxIndex = trs.length - 1;
	trs.each(function(i){
		var $this = $(this);
		var tds = $this.find("td");
		var name = $(tds[0]).text();
		var count = $this.find(":text").val();
		var unit = $(tds[1]).text();
		var unitPrice = $(tds[2]).text();
		result += (name+"*"+count+"*"+unit+"*"+unitPrice);
		if(i < maxIndex){
			result += ", ";
		}
	});
	
	return result;
}
</script>
</body>
</html>