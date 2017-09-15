<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>
<title>捞贝餐饮管理系统</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap-treeview.min.css">
<script src="${pageContext.request.contextPath }/js/bootstrap-treeview.min.js"></script>
<style type="text/css">
#tree{
	/* width: 15%;
	display: inline-block; */
}
.list-group-item{
	padding-top: 5px;
	padding-bottom: 5px;
}
/* .node-tree:not(.node-selected):hover{
	background-color: #dde0fb !important;
} */
.title{
	width: 100%;
	margin-bottom: -35px;
}

.user-info{
	position: relative;
    text-align: right;
    bottom: 40px;
    right: 8%;
}
#contentFrame{
	/* height: 100%; */
	width: 100%;
	overflow: visible;
}

</style>
</head>
<body>
<div class="title">
	<img src="${pageContext.request.contextPath }/img/title.png" alt="捞贝餐饮管理系统" width="100%" />
	<div class="user-info">
		<div class="btn-group">
		<button type="button" class="btn btn-default dropdown-toggle" 
				data-toggle="dropdown">
			${user.username }<span class="caret"></span>
		</button>
		<ul class="dropdown-menu" role="menu">
			<li><a href="#" onclick="showChangePwd();">修改密码</a></li>
			<li class="divider"></li>
			<li><a href="${ctx }/user/logout.do">退出</a></li>
		</ul>
		</div>
	</div>
</div>
<div class="containter">
	<div class="row" style="margin-right: 0;">
		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2"  style="padding-right: 0; padding-left: 0;">
			<div id="tree" class="well">
			</div>
		</div>
		<div class="col-lg-10 col-md-10 col-sm-2 col-xs-10" style="padding-right: 0; padding-left: 0;">
			<iframe id="contentFrame" frameborder="0"></iframe>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		var treeData= [
			{
				text: '菜谱',
				state: {
					expanded: true
				},
				nodes: [
					{
						text: '菜谱',
						href: '${pageContext.request.contextPath }/cookBook/findAllCookBook.do'
					},
					{
						text: '酒水单',
						href: '${pageContext.request.contextPath }/drink/findAllDrink.do'
					}
				],
			},
			{
				text: '库存',
				state: {
					expanded: true
				},
				nodes: [
					{
						text: '食材',
						href: '${pageContext.request.contextPath }/stock/findAllStock.do?stockType=%E9%A3%9F%E6%9D%90'
					},
					{
						text: '酒水',
						href: '${pageContext.request.contextPath }/stock/findAllStock.do?stockType=%E9%85%92%E6%B0%B4'
					},
					{
						text: '调料',
						href: '${pageContext.request.contextPath }/stock/findAllStock.do?stockType=%E8%B0%83%E6%96%99'
					},
					{
						text: '易耗品',
						href: '${pageContext.request.contextPath }/stock/findAllStock.do?stockType=%E6%98%93%E8%80%97%E5%93%81'
					},
					{
						text: '固定资产',
						href: '${pageContext.request.contextPath }/stock/findAllStock.do?stockType=%E5%9B%BA%E5%AE%9A%E8%B5%84%E4%BA%A7'
					},
				]
			},
			{
				text: '消耗列表',
				href: '${pageContext.request.contextPath }/consume/listAll.do'
			},
			{
				text: '采购列表',
				href: '${pageContext.request.contextPath }/purchase/listAll.do'
			},
			{
				text: '报表',
				state: {
					expanded: true
				},
				nodes: [
					{
						text: '消耗与采购',
						href: '${pageContext.request.contextPath }/chart/consumeAndPurchase.do'
					},
					{
						text: '经营报表',
						href: '${pageContext.request.contextPath }/chart/operatingChart.do'
					},

				]
			}
			<c:if test="${user.isAdmin==1}">
			,{
				text: '系统管理',
				href: '${pageContext.request.contextPath }/user/admin/listAll.do'
			}
			</c:if>
			];
		$("#tree").treeview({
				color: "#428bca",
				onhoverColor: "#dde0fb",
				backColor: 'transparent',
				showBorder: false,
				data: treeData,
				onNodeSelected: function (event, node) {
		            $(this).treeview('unselectNode', [node.nodeId, { silent: false }]);
		        },
		        onNodeUnselected: function (event, node) {
		            $(this).treeview('selectNode', [node.nodeId, { silent: true }]);
		        },
		        onNodeCollapsed:function(event, node){
		            $.each(node.nodes, function(){
		                if(this.state.selected){
		                   $('#treeview').treeview('selectNode', 
		                                  [ node.nodeId, { silent: true } ]); 
		                   return;
		                }
		            });     
		        }
	        });
		
		$("#tree").on('click', openContent);
		
	});
	
	
	
	function openContent(){
		var selects = $('#tree').treeview('getSelected');
		if(selects.length == 0){
			return;
		}
		var node = selects[0];
		var contentFrame = $("#contentFrame")[0];
		if(node.href == contentFrame.src){
			contentFrame.contentWindow.location.reload();
		}else{
			contentFrame.src=node.href;
		}
	}
	function resizeContentFrame(){
		/* var iframe = $("#contentFrame")[0];  
	    try {  
	  
	        var height = iframe.contentWindow.document.documentElement.scrollHeight;  
	  
	        iframe.height = height;  
	  
	    } catch (ex) {}   */
	    $("#contentFrame").height($(window).height() - $(".title").height());
		
	}
	$("#contentFrame").on('load', function () {
		resizeContentFrame();
		/* $($("#contentFrame")[0].contentWindow.document.body).on('click', function(){
			resizeContentFrame();
		}); */
	});
	window.onresize=resizeContentFrame;
	
	/* $("#contentFrame").click(resizeContentFrame); */
	
	/* window.setInterval(resizeContentFrame, 200); */

	function showChangePwd(){
		$("#changePwd").modal('show');
	}
	
	function changePwd(btn){
		var canSubmit = true;
		var $btn = $(btn);
		var oldBtnTxt = $btn.text();
		$btn.attr("disabled", "disabled");
		$btn.text("修改中");
		$("#pwdMsgDiv").hide();
		if($(":input[name='newPwd']").val() != $(":input[name='reNewPwd']").val()){
			$("#pwdMsg").text("新密码两次输入的不一致");
			$("#pwdMsgDiv").show();
			canSubmit = false;
		}
		
		if(!canSubmit){
			$btn.text(oldBtnTxt);
			$btn.removeAttr("disabled");
			return;
		}
		
		$.post("${ctx}/user/changePwd.do", $("#changePwdForm").serialize(), function(data){
			if(data.status == 200){
				$("#pwdMsg").text(data.msg);
				$("#pwdMsgDiv").removeClass("alert-danger").addClass("alert-success").show();
				setTimeout(() => {
					window.location.href="${ctx}/user/login.do";
				}, 3000);
			}else{
				$("#pwdMsg").text(data.msg);
				$("#pwdMsgDiv").show();
				$btn.text(oldBtnTxt);
				$btn.removeAttr("disabled");
			}
		}, "json");
		
	}
</script>
<div class="modal fade" id="changePwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
            </div>
            <div class="modal-body">
		        <div id="pwdMsgDiv" class="alert alert-danger" style="display: none;">
					<!-- <a href="#" class="close" data-dismiss="alert">&times;</a> --> <strong id="pwdMsg"></strong>
				</div>
	            <form id="changePwdForm" class="form-horizontal" role="form">
					<div class="form-group">
						<label for="oldPwd" class="col-sm-4 control-label">原密码</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" name="oldPwd" id="oldPwd" 
								   placeholder="请输入原密码">
						</div>
					</div>
					<div class="form-group">
						<label for="newPwd" class="col-sm-4 control-label">新密码</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" name="newPwd" id="newPwd" 
								   placeholder="请输入新密码">
						</div>
					</div>
					<div class="form-group">
						<label for="reNewPwd" class="col-sm-4 control-label">再输入一次新密码</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" name="reNewPwd" id="reNewPwd" 
								   placeholder="请再次输入新密码">
						</div>
					</div>
				</form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="changePwd(this);" >提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>