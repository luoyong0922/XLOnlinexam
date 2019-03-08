<html>
<head>
    <title>我的课程</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="margin-top: 40px;">
<#--<form action="${rc.contextPath}/getAllCourseByPage" method="post">-->
    <#--课程名称:<input type="text" name="courseName" value="${courseName}"/>-->
    <#--<input type="text" name="pageIndex" id="pageIndex" value="1" style="display: none"/>-->
    <#--<input type="submit" value="搜索">-->
<#--</form>-->
    <table class="table table-hover" id="Test">
        <tr>
            <#--<th>序号</th>-->
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
            <#--<td >${courseManage?counter}</td>-->
            <td style="display: none" id="teaccourseid">${courseManage.id!}</td>
            <td>${courseManage.courseName!}</td>
            <td>${courseManage.teacName!}</td>
            <td>${courseManage.startTime?string("yyyy-MM-dd")}</td>
            <td>${courseManage.endTime?string("yyyy-MM-dd")}</td>
            <td>${courseManage.courseType!}</td>
            <td>${courseManage.courseCredit!}</td>
            <td style="text-align: center;">
                <a href="${rc.contextPath}/studentController/getHomework?tcI=${courseManage.id!}">查看作业通知</a><br>
                <a href="${rc.contextPath}/paperController/getPaperStandard?cN=${courseManage.courseName!}&tI=${courseManage.id!}">查看考试通知</a>
            </td>
        </tr>
    </#list>

    </tbody>
    </table>
<#include "../pageHelper2.ftl"/>
</div>


</body>
</html>