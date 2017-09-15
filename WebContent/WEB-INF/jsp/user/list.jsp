<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>
<title>捞贝餐饮管理系统 - 用户管理</title>
<style type="text/css">
.mainContent{
	background: #fff;
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
	<h1 class="text-center">用户管理</h1>
	<div class="mainContent">
		<form id="contentForm" method="post">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th class="">用户名</th>
						<th class="">注册日期</th>
						<th class="">是否管理员</th>
						<th class="">状态</th>
						<th class="op-td">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="cb">
						<tr id="user-${cb.uid }">
							<td class="username">${cb.username }</td>
							<td class="password hidden">${cb.password }</td>
							<td class="createTime">
								<fmt:formatDate value="${cb.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td class="isAdmin">
								<c:if test="${cb.isAdmin==1 }">是</c:if>
								<c:if test="${cb.isAdmin==0 }">否</c:if>
							</td>
							<td class="status">
								<c:if test="${cb.status==0 }">禁用</c:if>
								<c:if test="${cb.status==1 }">启用</c:if>
							</td>
							<td class="op-td">
								<button type="button" class="btn btn-info" onclick='edit(${cb.uid})'>修改</button>
							</td>
						</tr>
						
					</c:forEach>
				</tbody>
			</table>
		</form>
		<div class="text-right">
			<button type="button" class="btn btn-success" onclick="add();">新增</button>
		</div>
	
	</div>

</div>
<script type="text/javascript">
	function edit(uid){
		var form = $("#editModal form");
		var username = $.trim($("#user-"+uid+" .username").text());
		form.find(":input[name='username']").val(username);
		
		var password = $.trim($("#user-"+uid+" .password").text());
		form.find(":input[name='password']").val(password);
		
		var isAdmin = $.trim($("#user-"+uid+" .isAdmin").text());
		var isAdminNum = isAdmin == '是' ? 1 : 0;
		form.find(":input[name='isAdmin'][value='"+isAdminNum+"']").attr("checked", "checked");
		
		var status = $.trim($("#user-"+uid+" .status").text());
		var statusNum = status == '启用' ? 1 : 0;
		form.find(":input[name='status'][value='"+statusNum+"']").attr("checked", "checked");
		
		$("#editModal :input[name='uid']").val(uid);
		$("#editModal").modal('show');
	}
	function add(){
		$("#editModal form")[0].reset();
		$("#editModal").modal('show');
	}
	
	function save(){
		$("#editModal form")[0].submit();
	}
	
	
</script>

<!-- 模态框（Modal） -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" 
						aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					编辑用户
				</h4>
			</div>
			<div class="modal-body">
			<form class="form-horizontal" role="form" action="${ctx }/user/admin/updateOrAddUser.do">
				<input type="hidden" name="uid" />
				<div class="form-group">
					<label for="username" class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="username" id="username" 
							   placeholder="请输入用户名">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="password" id="password" 
							   placeholder="请输入密码">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">管理员</label>
					<div class="col-sm-10">
						<label class="radio-inline">
							<input type="radio" name="isAdmin" value="1" > 是
						</label>
						<label class="radio-inline">
							<input type="radio" name="isAdmin" value="0" checked> 否
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">状态</label>
					<div class="col-sm-10">
						<label class="radio-inline">
							<input type="radio" name="status" value="0" > 禁用
						</label>
						<label class="radio-inline">
							<input type="radio" name="status" value="1" checked> 启用
						</label>
					</div>
				</div>
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" 
						data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary" onclick="save();">
					保存
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->


</body>
</html>