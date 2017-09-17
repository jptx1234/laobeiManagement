<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include>
<title>捞贝餐饮管理系统 - 登录</title>
<style>
body{
	background: url("${ctx}/img/login_bg.png");
	background-repeat:no-repeat;
	background-size: cover;
}
.center-block {
	margin: auto;
	position: absolute;
	top: 50%;
	left: 50%;
	width: 60%;
	height: 50%;
	min-height: 330px;
	-webkit-transform: translate(-50%, -50%);
	-ms-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
}

.center-content {
	width: 100%;
	height: 100%; display : flex;
	align-items: center;
	padding: 20px;
	background-color: rgba(0, 0, 0, 0.25);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .05);
	border-radius: 6px;
	display: flex;
}

.center-img, .center-loginblock {
	flex-grow: 1;
	width: 50%;
	text-align: center;
	height: 100%;
}

.center-img {
	border-right: 1px #f9f3f3 solid;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.center-loginblock form {
	padding: 40px 20px 20px 20px;
	height: 100%;
	margin-left: 20px;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}

.tips {
	color: red;
}
.input-group{
	width: 100%;
}
.input-tip{
	font-size: 20px;
	margin-right: 20px;
	color: #ffffff;
}
.input-field{
	font-size: 20px;
    border-width: 0 0 1px 0;
    border-color: #fff;
    background-color: rgba(255, 255, 255, 0);
    color: #fff;
    outline: none;
}
.login-btn{
	height: 16%;
	font-size: 18px;
}
</style>
</head>
<body>
	<%-- <jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include> --%>
	<div class="center-block">
		<div class="alert alert-danger"
			<c:if test="${msg==null }">style="display: none;"</c:if>>
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>${msg }</strong>
		</div>
		<div class="center-content">
			<div class="center-img">
				<img alt="用户登录"
					src="${pageContext.request.contextPath }/img/login_logo.png" />
			</div>
			<div class="center-loginblock">
				<form method="post" role="form">
					<div class="input-group">
						<!-- <span class="input-group-addon" id="sizing-addon1"><i
							class="glyphicon glyphicon-user" aria-hidden="true"></i></span> <input
							type="text" class="form-control" id="userid" name="username"
							placeholder="请输入用户名" /> -->
							<span class="input-tip">账号</span>
							<input type="text" name="username" value="${username }" class="input-field" >
					</div>
					<div class="input-group">
						<!-- <span class="input-group-addon" id="sizing-addon1"><i
							class="glyphicon glyphicon-lock"></i></span> <input type="password"
							class="form-control" id="password" name="password"
							placeholder="请输入密码" /> -->
							<span class="input-tip">密码</span>
							<input type="password" name="password" value="${password }" class="input-field" >
					</div>
					<button type="submit" class="btn btn-info btn-block login-btn">登录</button>
				</form>
			</div>
		</div>
	</div>


</body>
</html>