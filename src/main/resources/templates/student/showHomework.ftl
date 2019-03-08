<html>
<head>
    <title>作业通知</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container center-block" style="margin-top: 50px;">
<#if (homework.id)??>

    课程名称：<span style="font-size: 18px;font-weight: 500;">${homework.courseName!}</span><br/>
    布置时间：<span style="font-size: 18px;font-weight: 300;">${homework.date?string("yyyy-MM-dd")}</span><br/>
    作业内容：<br/>
    <textarea name="homeworkContent" rows="6" cols="80">${homework.homeworkContent!}</textarea>
<#else>
    <span style="font-size: 25px;font-weight: 600;margin:auto auto;">暂无作业。</span>
</#if>
</div>
</body>
</html>