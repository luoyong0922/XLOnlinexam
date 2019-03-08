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
         <#if testTime??>
        <tr>
            <td>考试时长</td>
            <td><strong>${testTime!}</strong>分钟</td>
        </tr>
    </table>
        <table class="table table-hover" id="Test">
            <thead>
            <tr>
                <th>序号</th>
                <th>试题类型</th>
                <th>试题数量</th>
                <th>试题分值</th>
            </tr>
            </thead>
            <tbody>

                    <#list paperStandards as paperStandard>
                    <tr>
                        <td>${paperStandard?counter}</td>
                        <td>${paperStandard.testType!}</td>
                        <td>${paperStandard.testAmount!}</td>
                        <td>${paperStandard.testValue!}</td>
                    </tr>
                    </#list>
            </tbody>
        </table>
                <a href="${rc.contextPath}/paperController/intoTest/${testTime!}?teaccourseid=${teaccourseId!}&cN=${courseName!}" class="btn btn-info center-block">进入考试</a>
         <#else>
                    <tr><td></td><td style="font-size: 20px;font-weight:400;">暂无通知</td></tr>
          </tbody>
        </table>
         </#if>


</div>
</body>
</html>