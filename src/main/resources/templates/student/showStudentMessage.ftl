<html>

	<head>
		<title>学生信息</title>
		<link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
		<script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
	</head>

	<body>
		<div class="container">
			<form role="form" id="modifyStu" action="${rc.contextPath}/studentController/updateStudentMsg" method="POST">
				<input type="hidden" name="id" value="${student.id }" />
				<table class="table table-hover center-block" style="margin-top: 80px;width: 400px;">
					<tr>
						<td>学号</td>
						<td><input type="text" name="stuNum" value="${student.stuNum}" readonly="true" /></td>
					</tr>
					<tr>
						<td>密码</td>
						<td>
							<input type="password" value="......" readonly="true" />
							<input type="password" name="stuPassword" value="${student.stuPassword}"  hidden="true"/>
						</td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="stuName" value="${student.stuName}" /></td>
					</tr>
					<tr>
						<td>性别</td>
						<td>
							<input type="radio" name="stuGender" value="男" id="male">男&emsp;
							<input type="radio" name="stuGender" value="女" id="female">女
						</td>
					</tr>
					<tr>
						<td>电话</td>
						<td><input type="text" name="stuPhone" value="${student.stuPhone}" /></td>
					</tr>
					<tr>
						<td>住址</td>
						<td><input type="text" name="stuAddress" value="${student.stuAddress}" /></td>
					</tr>
					<tr>
						<td>密保问题</td>
						<td><input type="text" name="stuQuestion" value="${student.stuQuestion}" /></td>
					</tr>
					<tr>
						<td>密保回答</td>
						<td><input type="text" name="stuKey" value="${student.stuKey}" /></td>
					</tr>
				</table>
			</form>
			<input type="button" value="保存修改" class="btn btn-info center-block" style="width: 33%; margin-left: 28%;" onclick="save();">
		</div>
		<script>
			window.onload = function() {
				var gender = '${student.stuGender}';
				if(gender === '男') {
					document.getElementById('male').checked = true;
				} else {
					document.getElementById('female').checked = true;
				}
			}

			function save() {
				$('#modifyStu').submit();
			}
		</script>
	</body>

</html>