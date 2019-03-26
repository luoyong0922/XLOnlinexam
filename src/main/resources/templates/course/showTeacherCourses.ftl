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
        <#if (pageInfo.total > 0)>
    <#list pageInfo.list as teacCourse>
        <tr>
            <td>${teacCourse.courseName}</td>
            <td>${teacCourse.courseCredit}</td>
            <td>${teacCourse.teacName}</td>
            <td>${teacCourse.startTime?string("yyyy-MM-dd")}</td>
            <td>${teacCourse.endTime?string("yyyy-MM-dd")}</td>
            <td style="text-align: center;">
                <a href="${rc.contextPath}/homeworkController/getHomework?tcId=${teacCourse.id!}">查看作业</a>
                <a href="${rc.contextPath}/paperController/toPublicPaper?id=${teacCourse.id}&courseName=${teacCourse.courseName}">发布测试</a>
            </td>
        </tr>
    </#list>
    <#else>
    <tr><td colspan="9" style="text-align:center;">暂无数据</td></th>
    </#if>

        </tbody>
    </table>
    <#if (pageInfo.total > 6)>
        <#include "../pageHelper2.ftl"/>
    </#if>
</div>

</body>
</html>