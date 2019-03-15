<html>
<head>
    <title>查看成绩排名</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${rc.contextPath}/static/css/bootstrap.min.css">
</head>
<body>
<div class="container">

    <div class="table-responsive">
        <table class="table table-hover">
            <caption style="text-align:center"><span style="font-size: 18px;font-weight: 600;">${courseName}</span>成绩排名表</caption>
            <thead>
                <tr>
                    <th>姓名</th>
                    <th>分数</th>
                    <th>名次</th>
                </tr>
            </thead>
            <tbody>

            <#list infoIds as entry >
            <tr>
                <td>${entry.key}</td>
                <td>${entry.value}</td>
                <td>${entry?counter}</td>
            </tr>
            </#list>

            </tbody>
        </table>
    </div>
</div>

</body>
</html>