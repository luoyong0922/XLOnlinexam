<html>

	<head>
		<title>老师信息</title>
		<link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
		<script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
	</head>

	<body>
		<div class="container">
			<form role="form" id="modifyTeac" action="${rc.contextPath}/teacherController/modifyTeacherMsg" method="POST">
				<input type="hidden" name="id" value="${teacher.id }" />
				<table class="table table-hover center-block" style="margin-top: 80px;width: 400px;">
					<tr>
						<td>教师工号</td>
						<td><input type="text" name="teacWorknum" value=" ${teacher.teacWorknum}" readonly="true" /></td>
					</tr>
					<tr>
						<td>密码</td>
						<td>
							<input type="password" value="......" readonly="true" />
							<input type="password" name="teacPassword" value="${teacher.teacPassword}" hidden="true" />
						</td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="teacName" value="${teacher.teacName}" /></td>
					</tr>
					<tr>
						<td>性别</td>
						<td>
							<input type="radio" name="gender" value="男" id="male">男
							<input type="radio" name="gender" value="女" id="female">女
						</td>
					</tr>
					<tr>
						<td>电话</td>
						<td><input type="text" name="teacPhone" value="${teacher.teacPhone}" /></td>
					</tr>
					<tr>
						<td>出生日期</td>
						<td><input type="text" id="age" name="teacBirth" value="${teacher.teacBirth?string(" yyyy-MM-dd ")}" /></td>
					</tr>
					<tr>
						<td>密保问题</td>
						<td><input type="text" name="teacQuestion" value="${teacher.teacQuestion}" /></td>
					</tr>
					<tr>
						<td>密保回答</td>
						<td><input type="text" name="teacKey" value="${teacher.teacKey}" /></td>
					</tr>

				</table>
			</form>
			<input type="button" value="保存修改" class="btn btn-info center-block" style="width: 33%; margin-left: 28%;" onclick="save();">
		</div>
		<script>
			window.onload = function() {
				var gender = '${teacher.teacGender}';
				if(gender === '男') {
					document.getElementById('male').checked = true;
				} else {
					document.getElementById('female').checked = true;
				}

			}

			function save() {
				$('#modifyTeac').submit();
			}
		</script>
	</body>

</html>