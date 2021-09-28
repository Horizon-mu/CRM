<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String basePath = request.getScheme() + "://" +
			request.getServerName() + ":" + request.getServerPort() +
			request.getContextPath() + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script>
		$(function (){
			if (window.top != window){
				window.top.location = window.location;
			}
			//页面加载完毕后，用户名自动获得焦点
			$("#loginAct").focus();
			//页面加载完毕后，将用户文本框中的内容清空
			$("#loginAct").val("");
			//为登录按钮绑定点击事件
			$("#submitBtn").click(function (){
				login();
			})
			//为当前窗口绑定键盘事件
			$(window).keydown(function (event) {
				if (event.keyCode == 13){
					login();
				}
				//alert(event.keyCode);
			})
		})
		function login() {
			//alert("执行验证登录操作");
			//验证账号和密码
			//去掉文本框中的左右空格
			var loginAct = $.trim($("#loginAct").val());
			var loginPwd = $.trim($("#loginPwd").val());
			if (loginAct == "" || loginPwd == ""){
				$("#msg").html("账号密码不能为空");
				return false;
			}
			$.ajax({
				url : "user/login.do",
				data : {
					"loginAct" : loginAct,
					"loginPwd" : loginPwd
				},
				type:"post",
				dataType:"json",
				success:function(result){
					/*
					* result {"success" : true/false,"msg" : "错误信息"}
					* */

					if (result.success){
						window.location.href="workbench/index.jsp";
					}else{
						$("#msg").html(result.msg);
						$("#msg").css("color","red");
					}
				}
			})
		}


	</script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 100%;">
		<img src="image/iceworld.jpg" style="width: 100%;position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM-智慧办公后台管理系统&nbsp;<span style="font-size: 12px;">&copy;2021 Horizon</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form class="form-horizontal" role="form" method="post">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" placeholder="用户名" id="loginAct">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" placeholder="密码" id="loginPwd">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						
							<span id="msg" style=""></span>
						
					</div>
					<button type="button" id="submitBtn" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;" >登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>