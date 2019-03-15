<html>
<head>
    <title>我的课程</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="margin-top: 50px;">
    <table class="table table-hover" id="Test">
        <thead>
        <tr>
            <th>课程名称</th>
            <th>课程学分</th>
            <th>授课老师</th>
            <th>开课时间</th>
            <th>结课时间</th>
            <th colspan="4" style="text-align: center;">操作</th>
        </tr>
        </thead>
        <tbody>
    <#list pageInfo.list as teacCourse>
        <tr>
            <td>${teacCourse.courseName}</td>
            <td>${teacCourse.courseCredit}</td>
            <td>${teacCourse.teacName}</td>
            <td>${teacCourse.startTime?string("yyyy-MM-dd")}</td>
            <td>${teacCourse.endTime?string("yyyy-MM-dd")}</td>
            <td style="text-align: center;">
                <a href="${rc.contextPath}/teacherController/toPublicHomewWork?tcid=${teacCourse.id}&cname=${teacCourse.courseName}">布置作业</a>
                <a href="${rc.contextPath}/paperController/toPublicPaper?id=${teacCourse.id}&courseName=${teacCourse.courseName}">发布考试</a>
            </td>
        </tr>
    </#list>

        </tbody>
    </table>
<#--<#include "../pageHelper2.ftl"/>-->
</div>

</body>
</html>