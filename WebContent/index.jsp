<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>捞贝餐饮管理系统 - 登录</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<style>
.center-block {
	margin: auto;
	position: absolute;
	top: 50%;
	left: 50%;
	width: 60%;
	height: 50%;
	display: flex;
	align-items: center;
	padding: 20px;
	background-color: #f5f5f5;
	border: 1px solid #e3e3e3;
	box-shadow: inset 0 1px 1px rgba(0,0,0,.05);
	border-radius: 4px;
	-webkit-transform: translate(-50%, -50%);
	-ms-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
}
.center-img, .center-loginblock{
	flex-grow: 1;
	width: 50%;
	text-align: center;
}

.center-img {
	border-right: 1px #909090 solid;
}

.center-loginblock form {
	margin-left: 20px;
	display: flex;
	flex-direction: column;
	justify-content: space-around;
}

</style>
</head>
<body>
	<%-- <jsp:include page="/WEB-INF/jsp/common/head.jsp"></jsp:include> --%>
	<div class="center-block">
		<div class="center-img">
			<img alt="用户登录" src="${pageContext.request.contextPath }/img/login_logo.png" />
		</div>
		<div class="center-loginblock">
			<form action="/login" method="post" role="form">
				<div class="input-group input-group-md">
					<span class="input-group-addon" id="sizing-addon1"><i
						class="glyphicon glyphicon-user" aria-hidden="true"></i></span> <input
						type="text" class="form-control" id="userid" name="userid"
						placeholder="请输入用户ID" />
				</div>
				<div class="input-group input-group-md">
					<span class="input-group-addon" id="sizing-addon1"><i
						class="glyphicon glyphicon-lock"></i></span> <input type="password"
						class="form-control" id="password" name="password"
						placeholder="请输入密码" />
				</div>
				<br />
				<button type="submit" class="btn btn-success btn-block">登录</button>
			</form>
		</div>
	</div>
	
	
</body>
</html>