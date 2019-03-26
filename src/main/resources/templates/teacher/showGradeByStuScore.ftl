<html>
<head>
    <title>查看成绩排名</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${rc.contextPath}/static/css/bootstrap.min.css">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
</head>
<body>
<div class="container">

    <div class="table-responsive">
        <table class="table table-hover">
            <caption style="text-align:center"><span style="font-size: 18px;font-weight: 600;">${courseName}</span>成绩排名表&emsp;&emsp;&emsp;&emsp;<input type="button" class="btn btn-info" value="查看成绩统计表" onclick="javascript:toStatistics(${teacCourseId})"/></caption>
            <thead>
                <tr>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>分数</th>
                    <th>名次</th>
                </tr>
            </thead>
            <tbody>
            <#if infoIds??>
            <#assign y = maxGrade>
            <#assign x = 1>
            <#list infoIds as entry >
                <#if (entry.value == y)>
                <#else>
                    <#assign x = x+1>
                    <#assign y = entry.value>
                </#if>

            <tr>
                <td>${entry?counter}</td>
                <td>${entry.key}</td>
                <td><#if (entry.value>=0)>${entry.value}<#else>缺考</#if></td>
                <td>${x}</td>
            </tr>
            </#list>
            <#else>
            <tr>暂无数据</tr>
            </#if>

            </tbody>
        </table>
    </div>
</div>
<script>
function toStatistics(teacCourseId) {
        window.location = '${rc.contextPath}/achievementController/pieByTeacCourseId?teacCourseId=' + teacCourseId;
    }
</script>
</body>
</html>