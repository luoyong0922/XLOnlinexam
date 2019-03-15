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
        <td><strong>${courseName!}</strong></td>
    </tr>
         <#if paperStandards??>
        
    </table>
        <table class="table table-hover" id="Test">
            <thead>
            <tr>
                <th>序号</th>
                <th>测评单元</th>
                <th>测评周次</th>
                <th>测评时长（分钟)</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                    <#list paperStandards as paperStandard>
                    <tr>
                        <td>${paperStandard?counter}</td>
                        <td>第${paperStandard.testAmount!}单元</td>
                        <td>测评${paperStandard.testValue!}</td>
                        <td>${paperStandard.testTime!} min</td>
                        <td><a href="${rc.contextPath}/achievementController/getAllstuScore?teacCourseId=${paperStandard.id}" class="btn btn-info center-block">查看详情</a></td>
                    </tr>
                    </#list>
            </tbody>
        </table>
               
         <#else>
                    <tr><td style="font-size: 20px;font-weight:400;text-align: center;">暂无数据</td></tr>
          </tbody>
        </table>
         </#if>


</div>
</body>
</html>