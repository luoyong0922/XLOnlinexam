<html>
<head>
    <title>我的课程</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="margin-top: 40px;">
    <table class="table table-hover" id="Test">
        <tr>
            <th>课程名称</th>
            <th>授课老师</th>
            <th>开课时间</th>
            <th>结课时间</th>
            <th>课程类型</th>
            <th>课程学分</th>
            <th style="text-align: center;">通知</th>
        </tr>
        </thead>
        <tbody>
    <#list pageInfo.list as courseManage>
        <tr>
            <td style="display: none" id="teaccourseid">${courseManage.id!}</td>
            <td>${courseManage.courseName!}</td>
            <td>${courseManage.teacName!}</td>
            <td>${courseManage.startTime?string("yyyy-MM-dd")}</td>
            <td>${courseManage.endTime?string("yyyy-MM-dd")}</td>
            <td>${courseManage.courseType!}</td>
            <td>${courseManage.courseCredit!}</td>
            <td style="text-align: center;">
                <#if (opration != 2)>
                    <a href="${rc.contextPath}/homeworkController/getHomework?tcId=${courseManage.id!}">查看作业通知</a><br>
                </#if>
                <#if (opration != 1)>
                    <a href="${rc.contextPath}/paperController/getPaperStandard?cN=${courseManage.courseName!}&tI=${courseManage.id!}">查看测评通知</a>
                </#if>
            </td>
        </tr>
    </#list>

    </tbody>
    </table>
    <#if (pageInfo.total > 6)>
        <#include "../pageHelper2.ftl"/>
    </#if>
</div>


</body>
</html>