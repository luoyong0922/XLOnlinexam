<html>
<head>
    <title>发布作业</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container center-block" style="margin-top: 50px;">

<form  action="${rc.contextPath}/teacherController/publicHomeWork" method="post">
    <input type="text" name="teacCourseId" value="${teacCourseId}" style="display: none;">
    <input type="text" name="courseName" value="${courseName}" style="display: none;">
    课程名称：<span style="font-size: 18px;font-weight: 500;">${courseName!}</span><br/>
    作业内容：<br/>
    <textarea name="homeworkContent" rows="6" cols="80"></textarea><br/>
    <input type="submit" class="btn btn-info" value="发布作业"/>
</form>
</div>
</body>
</html>