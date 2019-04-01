<html>

	<head>
		<title>学生试卷列表</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="${rc.contextPath}/static/css/bootstrap.min.css">
		<script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>

	</head>

	<body>

		<div class="container">

			<h2 style="text-align: center">试卷列表</h2><br/>

			<div class="container">

				<table class="table table-hover" id="Test">
					<thead>
						<tr>
							<th>序号</th>
							<th>课程名称</th>
							<th>测评单元</th>
                            <th>测评周次</th>
							<th>学生姓名</th>
							<th>考试用时</th>
							<th>状态</th>
							<th>成绩</th>
							<th style="text-align:center">操作</th>
						</tr>
					</thead>
					<tbody>
						<#if (pageInfo.total > 0)>
							<#list pageInfo.list as stuScore>
								<tr <#if stuScore.paperState==0>class="table-warning"
									<#elseif stuScore.paperState==1>class="table-info" </#if> >
                                        <td>${stuScore?counter}</td>
                                        <td>${stuScore.courseName!}</td>
                                        <td>单元${stuScore.testUnit}</td>
                                        <td>测评${stuScore.testNum}</td>
                                        <td>${stuScore.stuName!}</td>
                                        <td>${stuScore.testTime!}</td>
                                        <td>
                                            <#if stuScore.paperState==0>待批阅
                                                <#elseif stuScore.paperState==1>已批阅</#if>
                                        </td>
                                        <td>${stuScore.score!}</td>
                                        <td style="text-align:center;">
                                            <a href="javascript:void(0);" onclick="toMaker(${stuScore.paperId},'${stuScore.courseName}',${stuScore.testTime!})">查看详情</a>
                                            <a href="javascript:void(0);" onclick="toScoreOrder(${stuScore.teacCourseId})">查看排名</a>
                                        </td>
                                </tr>
                            </#list>
						<#else>
							<tr>
								<td style="font-size: 25px;font-weight: 600;text-align: center;" colspan="8">
									暂无数据
								</td>
							</tr>
							</#if>
					</tbody>
				</table>
			</div>
                <#if (pageInfo.total > 6)>
                    <#include "../pageHelper2.ftl"/>
                </#if>
		</div>

		<script>
			function toStatistics() {
				window.location = '${rc.contextPath}/achievementController/pieByTeacCourseId?teacCourseId=${teacCourseId}';
			}

			function toScoreOrder(id) {
				window.location = '${rc.contextPath}/achievementController/showGradeOrder/${courseName}?teacCourseId='+ id;
			}
			function toMaker(paperId,courseName,testTime) {
                window.location = '${rc.contextPath}/paperController/toMarking/'+ paperId +'?courseName='+ courseName + '&testTime=' + testTime;
            }
		</script>

	</body>

</html>