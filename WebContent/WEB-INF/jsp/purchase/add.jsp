<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>
<title>捞贝餐饮管理系统 - 采购列表录入</title>
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
.uploadDiv{
	display: inline-block;
	margin-left: 10px;
	margin-bottom: 10px;
}
.imgPreview{
	width: 200px;
	height: 200px;
	display: table-cell;
    vertical-align: middle;
	border: 1px solid #000;
}
.img-control-width{
	width: 200px;
	height: auto;
}
.img-control-height{
	height: 200px;
	width: auto;
}
</style>
</head>
<body>
<div class="well">
<div class="">
<div class="pull-left add-content" style="position: fixed;right: 0;top: 0;padding: 20px;width: 16%;margin-right: 2%;height: 100%;">
	<select id="typeSelect" class="form-control" onchange="onSelectType();">
		<option id="typeSelectLoading" disabled selected>加载中……</option>
		<option value="sc">食材</option>
		<option value="js">酒水</option>
		<option value="tl">调料</option> 
		<option value="yhp">易耗品</option>
		<option value="gdzc">固定资产</option>
	</select>
	<select id="itemSelect" class="form-control" size=10 style="
    height: 80%;
    margin-top: 10px;
	">
	</select>
</div>
<form id="recipeForm" action="${pageContext.request.contextPath }/purchase/addPurchase.do" method="post" enctype="multipart/form-data">
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
				         <td><input type="text" data-type="name" class="form-control" disabled ></td>
				         <td><input type="text" data-type="unit" class="form-control" disabled ></td>
				         <td><input type="text" data-type="unitPrice" class="form-control"></td>
				         <td><input type="text" data-type="count" class="form-control"></td>
				         <td>
				         	<button type="button" class="btn btn-danger" onclick="deleteRow(this);"><span class="glyphicon glyphicon-trash"></span></button>
				         </td>
				      </tr>
				   </tbody>
			</table>
	      <button type="button" class="btn btn-default" onclick="addEntry('sc-table')">增加</button>
			<input type="hidden" name="sc" />
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
				         <td><input type="text" data-type="name" class="form-control" disabled ></td>
				         <td><input type="text" data-type="unit" class="form-control" disabled ></td>
				         <td><input type="text" data-type="unitPrice" class="form-control"></td>
				         <td><input type="text" data-type="count"  class="form-control"></td>
				         <td>
				         	<button type="button" class="btn btn-danger" onclick="deleteRow(this);"><span class="glyphicon glyphicon-trash"></span></button>
				         </td>
				      </tr>
				   </tbody>
			</table>
			<button type="button" class="btn btn-default" onclick="addEntry('js-table')">增加</button>
			<input type="hidden" name="js" />
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
				         <td><input type="text" data-type="name" class="form-control" disabled ></td>
				         <td><input type="text" data-type="unit" class="form-control" disabled ></td>
				         <td><input type="text" data-type="unitPrice" class="form-control"></td>
				         <td><input type="text" data-type="count"  class="form-control"></td>
				         <td>
				         	<button type="button" class="btn btn-danger" onclick="deleteRow(this);"><span class="glyphicon glyphicon-trash"></span></button>
				         </td>
				      </tr>
				   </tbody>
			</table>
			<button type="button" class="btn btn-default" onclick="addEntry('tl-table')">增加</button>
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
				         <td><input type="text" data-type="name" class="form-control" disabled ></td>
				         <td><input type="text" data-type="unit" class="form-control" disabled ></td>
				         <td><input type="text" data-type="unitPrice" class="form-control"></td>
				         <td><input type="text" data-type="count"  class="form-control"></td>
				         <td>
				         	<button type="button" class="btn btn-danger" onclick="deleteRow(this);"><span class="glyphicon glyphicon-trash"></span></button>
				         </td>
				      </tr>
				   </tbody>
			</table>
			<button type="button" class="btn btn-default" onclick="addEntry('yhp-table')">增加</button>
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
				         <td><input type="text" data-type="name" class="form-control" disabled ></td>
				         <td><input type="text" data-type="unit" class="form-control" disabled ></td>
				         <td><input type="text" data-type="unitPrice" class="form-control"></td>
				         <td><input type="text" data-type="count"  class="form-control"></td>
				         <td>
				         	<button type="button" class="btn btn-danger" onclick="deleteRow(this);"><span class="glyphicon glyphicon-trash"></span></button>
				         </td>
				      </tr>
				   </tbody>
			</table>
			<button type="button" class="btn btn-default" onclick="addEntry('gdzc-table')">增加</button>
			<input type="hidden" name="gdzc" />
		</div>
	</div>
	<div class="add-content">
		<h3 class="text-center">上传图片</h3>
		<div class="text-center content">
			<div  id="upload" style="text-align: left;">
				<div class="uploadDiv hidden">
					<div class="imgPreview"></div>
					<!-- <button type="button" class="btn btn-danger" onclick="deleteImg(this);"><span class="glyphicon glyphicon-trash"></span></button> -->
				</div>
			</div>
		<button id="addFileBtn" type="button" class="btn btn-default" onclick="showOpenFile();">增加</button>
		</div>
	</div>
	
	
	<div class="text-center" style="margin-top: 40px;">
		<button type="button" class="btn btn-primary btn-lg btn-block" onclick="save();">保存采购列表</button>
	</div>
</form>
</div>
</div>

<script type="text/javascript">
var stockData = null;

var yesterday = null;

var loading = false;

var supportImgPreview = true;

var fileCount = 0;

var fileInputCounter = 0;

$(function(){
	if(!(window.FileReader && window.File && window.FileList && window.Blob)){
		supportImgPreview = false;
	}
	$.get("${ctx}/consume/listAllStockJSON.do", {}, function(data){
		stockData = data;
		$("#typeSelectLoading").text("请选择种类");
		$("#itemSelect").dblclick(onSelectItem);
		loadYesterday();
	}, "json");
});

function loadYesterday(){
	$.get("${ctx}/purchase/getYesterday.do", {}, function(data){
		loading = true;
		
		for (var type in data){
			var selectType = $("#typeSelect option[value='"+type+"']");
			if(selectType.length != 0){
				$("#typeSelect").val(type);
				onSelectType();
				for(var i=0; i < data[type].length; i++){
					var selectItem = $("#itemSelect option[value='"+data[type][i]['name']+"']")
					if(selectItem.length != 0){
						$("#itemSelect :selected").removeAttr("selected");
						selectItem.attr("selected", "selected");
						onSelectItem();
					}
				}
			}
		}
		
		loading = false;
		$("#typeSelect")[0].selectedIndex = 0;
	}, "json");
}

function onSelectType(){
	var selectVal = $("#typeSelect").val();
	var itemSelect = $("#itemSelect");
	itemSelect.html("");
	var items = stockData[selectVal];
	for(var i=0; i < items.length; i++){
		var item = $('<option id="'+items[i]['id']+'" value="'+items[i]['name']+'" >'+items[i]['name']+'</option>');
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
	tds.find(":text[data-type='name']").val(item);
	var itemsOfType = stockData[type];
	for(var i = 0; i < itemsOfType.length; i++){
		if(itemsOfType[i]["id"]==itemId){
			if(itemsOfType[i].hasOwnProperty("unit")){
				tds.find(":text[data-type='unit']").val(itemsOfType[i]["unit"]);
			}else{
				$(tds[1]).text("份");
			}
			if(itemsOfType[i].hasOwnProperty("unitPrice")){
				tds.find(":text[data-type='unitPrice']").val(itemsOfType[i]["unitPrice"]);
			}
			itemsOfType.splice(i, 1);//提取完属性之后，把这个项从stockData中删除，让他不会再出现在选择框中
			break;
		}
	}
	eleModel.removeClass("hidden");
	tb.append(eleModel);
	$(".add-tip").removeClass("add-tip");
	onSelectType();
	if(!loading){
		eleModel.addClass("add-tip");
		window.location.hash = ("#"+eleModel.attr("id"));
		eleModel.find(":text").focus();
	}
	
}

function deleteRow(btn){
	var aim = $(btn).closest("tr");
	aim.fadeOut("fast", function(){
		aim.remove();
	});
}

function addEntry(tableName){
	var eleModel = $("#"+tableName+" tbody tr:first").clone();
	eleModel.find("input").val("");
	eleModel.find("input").removeAttr("disabled");
	$("#"+tableName+" tbody").append(eleModel);
	eleModel.removeClass("hidden");
}

function showOpenFile(){
	var input = $("<input type='file' class='hidden' name='img'  multiple />");
	$("#upload").append(input);
	fileInputCounter++;
	input.attr("id", "fileinput-"+fileInputCounter);
	input.change(function(){
		var files = input[0].files;
		if(files.length > 0){
			addFile(files, "fileinput-"+fileInputCounter);
		}
	});
	input.click();
}


function addFile(files, inputId){
	if((fileCount + files.length) > 10){
		alert("最多上传10张图片，已经有"+fileCount+"张，本次试图增加"+files.length+"张");
		return;
	}
	for(var i = 0; i < files.length; i++){
		if(!/image\/\w+/.test(files[i].type)){
	        alert(files[i].name+"不是图片");
	        $("#"+inputId).remove();
	        return;
	    }
	}
	for(var i = 0; i < files.length; i++){
		var file = files[i];
		var eleModel = $("#upload .uploadDiv:first").clone();
		$("#upload").append(eleModel);
		eleModel.removeClass("hidden").attr("data-belong", inputId+"@"+i);
		previewImg(eleModel, file);
	}
	fileCount += files.length;
}


function previewImg(div, file){
	if(!supportImgPreview){
		return;
	}
	var reader = new FileReader();
	var show = div.find(".imgPreview");
	
	reader.onload = function(e){
		var img = $('<img src="'+e.target.result+'" alt="'+file.name+'">');
		img.hide();
		img.on('load', function(){
			var naturalWidth = img[0].naturalWidth;
			var naturalHeight = img[0].naturalHeight;
			console.log(naturalWidth+" * "+naturalHeight);
			var imgControlCls = naturalWidth > naturalHeight ? "img-control-width" : "img-control-height";
			img.addClass(imgControlCls);
			img.show();
		});
        show.append(img);
    }
	reader.readAsDataURL(file);
}

function deleteImg(btn){
	var uploadDiv = $(btn).parent(".uploadDiv");
	var belong = uploadDiv.attr("data-belong");
	var belongArray = belong.split('@');
	var inputId = belongArray[0];
	var fileIndex = belongArray[1];
	$("#"+inputId)[0].files[fileIndex] = null;
	uploadDiv.remove();
	
}

function save(){
	var canSubmit = true;
	$(".has-error").removeClass("has-error").removeAttr("data-toggle").removeAttr("data-placement").removeAttr("title").removeAttr("data-original-title");
	$(":text").tooltip('destroy');
	$(".tooltip").remove();
	
	$(":text:visible").each(function(){
		var $this = $(this);
		if($this.val() == ""){
			$this.attr("data-placement", "bottom");
			$this.attr("title", "不能为空");
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
	
	
	var patrn = /^\d+(\.\d+)?$/;
	$(":text:visible[data-type='count']").each(function(){
		if (!patrn.exec(this.value)) {
			var $this = $(this);
			/* $this.attr("data-toggle", "tooltip"); */
			$this.attr("data-placement", "bottom");
			$this.attr("title", "必须输入数字");
			$this.addClass("has-error");
			$this.tooltip('show');
			canSubmit = false;
		}
	});
	
	for (var type in stockData){
		var tb = $("#"+type+"-table");
		tb.find(":text:enabled[data-type='name']").each(function(){
			var $this = $(this);
			var name = $this.val();
			var foundCount = 0;
			tb.find(":text[data-type='name']").each(function(){
				if($(this).val() == name){
					foundCount += 1;
				}
			});
			if(foundCount > 1){
				alert("发现"+foundCount+"个"+name+"，请勿重复添加");
				canSubmit = false;
			}
		});
	}
	
	if(!canSubmit){
		return;
	}
	
	for (var type in stockData){
		$(":input[name='"+type+"']").val(getTotalString(type+"-table"));
	}
	
	if(canSubmit){
		$("#saveBtn").attr("disabled", "disabled");
		$("#saveBtn").text("正在保存列表并更新库存，请耐心等候");
		$("#recipeForm")[0].submit();
	}
}
function getTotalString(id){
	var result = "";
	var trs = $("#"+id+" tbody tr:visible");
	var maxIndex = trs.length - 1;
	trs.each(function(i){
		var $this = $(this);
		var name = $this.find(":text[data-type='name']").val();
		var unit = $this.find(":text[data-type='unit']").val();
		var unitPrice = $this.find(":text[data-type='unitPrice']").val();
		var count = $this.find(":text[data-type='count']").val();
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