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
						</#if>
					</td>
				</tr>
                <tr>
                    <td>第<span>${standardMap["testAmount"]}</span>单元</td>
                    <td>测试<span>${standardMap["testValue"]}</span></td>
                </tr>
				<tr>
					<td>考试时长</td>
					<td><span>${standardMap["testTime"]}</span>分钟</td>
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
						<td>单选题</td>
						<td>${standardMap["selCount"]}</td>
						<td>${standardMap["selVal"]}</td>
					</tr>
					<tr>
						<td>多选题</td>
						<td>${standardMap["mutilCount"]}</td>
						<td>${standardMap["mutilVal"]}</td>
					</tr>
					<tr>
						<td>判断题</td>
						<td>${standardMap["judgeCount"]}</td>
						<td>${standardMap["judgeVal"]}</td>
					</tr>
					<tr>
						<td>填空题</td>
						<td>${standardMap["fillCount"]}</td>
						<td>${standardMap["fillVal"]}</td>
					</tr>
					<tr>
						<td>计算题</td>
						<td>${standardMap["calculateCount"]}</td>
						<td>${standardMap["calculateVal"]}</td>
					</tr>
					<tr>
						<td>主观题</td>
						<td>${standardMap["subjectCount"]}</td>
						<td>${standardMap["subjectVal"]}</td>
					</tr>
				</tbody>
			</table>
			<input type="button" class="btn btn-info" style="padding:10px 76px; font-size:19px; margin-left: 43%;" value="开始测评" id="startTest" />
			<div id="loading" style="display: none;"></div>
	</body>
</html>