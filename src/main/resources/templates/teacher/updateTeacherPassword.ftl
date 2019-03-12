<html>

	<head>
		<title>修改密码</title>
		<link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
	</head>

	<body>
		<div class="container">
			<div class="center-block" style="width:40%;">
				<h2 style="text-align: center;">修改密码</h2>
				<form id="modify" style="width:177px;" class="center-block">
					<input type="hidden" name="id" value="${teacher.id}"> 教师工号:
					<br/>
					<input type="text" name="teacWorknum" value="${teacher.teacWorknum}" readonly><br/> 请输入旧密码:
					<br/>
					<input type="password" name="oldPwd" id="oldPwd"><br/> 请输入新密码:
					<br/>
					<input type="password" name="teacPassword" id="pwd1"><br/> 请确认新密码:
					<br/>
					<input type="password" name="teacPassword2" id="pwd2"><br/>
				</form>

				<input type="button" value="确定" class="btn btn-info center-block" style="width: 40%;" onclick="checkPwd()">
			</div>
		</div>
		<script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
		<script>
			function checkPwd() {
				if($("#pwd1").val() == $("#pwd2").val()) {
					var form = $("#modify").serialize();
					$.ajax({
						type: "post",
						dataType: "json",
						contentType: "application/x-www-form-urlencoded",
						url: '${rc.contextPath}/teacherController/modifyTeacherMsg',
						data: form,
						success: function(data) {
							if(data.code == "success") {
								alert("密码修改成功，请注销后重新登录系统！");
								window.location = "${rc.contextPath}/teacherController/showTeacherMessage/${teacher.teacWorknum}";
							} else {
								alert(data.message);
							}
						}
					});
				} else {
					alert("新密码不一致，请确认后重新提交！");
				}

			}
		</script>
	</body>

</html>