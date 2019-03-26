<html>
	<head>
		<title>考试标准</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
		<script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
		<script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
	</head>

	<body>
		<div class="container center-block" style="margin-top: 50px;">
			<table class="table">

				<tr>
					<td>课程名称</td>
					<td>
						<#if courseName??>
							${courseName}
							<input type="text" id="select" name="id" value="${teaccourseId}" hidden="true">
						<#else>
							<select id="select" name="id" class="item-select" style="border-radius: 5px;height:34px;">
								<#list teacCourses as teacCourse>
									<option value="${teacCourse.id}">${teacCourse.courseName}</option>
								</#list>
							</select>
						</#if>
					</td>
				</tr>
        <tr>
            <td>第<input type="number" name="testAmount" id="u1">单元</td>
            <td>测试<input type="number" name="testValue" id="u2"></td>
        </tr>
				<tr>
					<td>考试时长</td>
					<td><input type="number" name="testTime" id="t1">分钟</td>
				</tr>
			</table>

			<h3>试题模块：</h3>
			<table class="table table-hover" id="Test">
				<thead>
					<tr>
						<th>题型</th>
						<th>题量</th>
						<th>分值</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" id="d3" value="单选题" readonly></td>
						<td><input type="number" id="d4"></td>
						<td><input type="text" id="d5"></td>
					</tr>
					<tr>
						<td><input type="text" id="d6" value="多选题" readonly></td>
						<td><input type="number" id="d7"></td>
						<td><input type="text" id="d8"></td>
					</tr>
					<tr>
						<td><input type="text" id="d9" value="判断题" readonly></td>
						<td><input type="number" id="d10"></td>
						<td><input type="text" id="d11"></td>
					</tr>
					<tr>
						<td><input type="text" id="d12" value="填空题" readonly></td>
						<td><input type="number" id="d13"></td>
						<td><input type="text" id="d14"></td>
					</tr>
					<tr>
						<td><input type="text" id="d15" value="计算题" readonly></td>
						<td><input type="number" id="d16"></td>
						<td><input type="text" id="d17"></td>
					</tr>
					<tr>
						<td><input type="text" id="d18" value="主观题" readonly></td>
						<td><input type="number" id="d19"></td>
						<td><input type="text" id="d20"></td>
					</tr>
				</tbody>
			</table>
			<input type="button" class="btn btn-info" style="padding:10px 76px; font-size:19px; margin-left: 43%;" value="发布考试" id="publishpaper" />
			<div id="loading" style="display: none;"></div>
			<script>
				$('#publishpaper').click(function() {
                					var $teacCourseId = $('#select').val();
                					if($teacCourseId == null) {
                						alert("请选择课程");
                					} else {
                						var message = new Object();
                						// 教师课程id
                						message.teacCourseId = $('#select').val();
                						// 测试单元
                						message.testAmount = $('#u1').val();
                						// 测试次数
                						message.testValue = $('#u2').val();
                						// 测试用时
                						message.testTime = $('#t1').val();

                						//处理 题型，题量， 分值 信息
                						var time = $(':input[id ^="d"]');
                						var formdata = "";
                						time.each(function() {
                							if($(this).val() != null && "" != $(this).val()) {
                								formdata += $(this).val() + ",";
                							} else {
                								formdata += "0,";
                							}
                						})
                						// 去除最后多余的 “，”
                						formdata = formdata.substring(0, formdata.length - 1);
                						// 将测试标准 封装成 json 格式的数据
                						var standaraData = formdata.split(",");
                						var testType = "{PaperStandard:[";
                						var data = new Object();

                						for (var i = 0; i < standaraData.length; ) {
                							data.testType = standaraData[i];
                							data.testAmount = 0;
                							data.testValue = 0;
                							if(standaraData[i+1] > 0){
                								data.testAmount = standaraData[i+1];
                								data.testValue = standaraData[i+2];
                							}
                							i += 3;
                							testType += JSON.stringify(data)+",";

                						}
                						testType = testType.substring(0, testType.length - 1)+"]}";
                						console.log(testType)
                						message.testType = testType;
                						console.log(message)
                						var json = JSON.stringify(message);
                						console.log(json);

                						$.ajax({
                							url: '${rc.contextPath}/paperController/doPublicPaper',
                							type: "POST",
                							dataType: "json",
                							data: json,
                							contentType: "application/json",
                							beforeSend: function() {
                								$("#loading").text("正在提交...");
                								$("#loading").show();
                							},
                							success: function(data) {
                								if(data.code == "success") {
                									$("#loading").hide();
                									alert("保存成功!");
                									window.location = "${rc.contextPath}/courseController/getCourseMessage?role=2";
                								} else {
                									$("#loading").hide();
                									alert(data.message);

                								}
                							},
                							error: function() {
                								alert("找不到服务器");
                							}
                						});
                					}
                				});
			</script>

	</body>

</html>