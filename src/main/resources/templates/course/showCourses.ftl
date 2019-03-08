<html>
<head>
    <title>课程管理</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="margin-top: 10px;">
<form action="${rc.contextPath}/courseController/getCourseMessage?role=3" method="post">
    课程名称:<input type="text" name="courseName" value="${courseName!}"/>
    授课老师：<input type="text" name="teacName" value="${teacName!}"/>
    <input type="text" name="pageIndex" id="pageIndex" value="1" style="display: none"/>
    <input type="submit" class="btn btn-info" value="搜索">
    <input type="button" class="btn btn-success" value="添加课程" onclick="javascript:addCourse()"/>
</form>

    <table class="table table-hover" id="Test">
        <thead>
        <tr>
            <th>序号</th>
            <th>课程编号</th>
            <th>课程名称</th>
            <th>授课老师</th>
            <th>课程学分</th>
            <th>开课时间</th>
            <th>结课时间</th>
            <th style="text-align: center;">操作</th>
        </tr>
        </thead>
        <tbody>
    <#list pageInfo.list as courseManage>
        <tr>
            <td>${courseManage?counter}</td>
            <td>${courseManage.courseNum}</td>
            <td>${courseManage.courseName}</td>
            <td>${courseManage.teacName!}</td>
            <td>${courseManage.courseCredit}</td>
            <td>${courseManage.startTime?string("yyyy-MM-dd")}</td>
            <td>${courseManage.endTime?string("yyyy-MM-dd")}</td>
            <td style="text-align: center;">
                <a href="${rc.contextPath}/adminController/toModifyTeacCourse/${courseManage.id}">修改</a>
                <a href="javascript:deleteCourse('${courseManage.id }','${courseManage.courseName}')">删除</a>
            </td>
        </tr>
    </#list>
        </tbody>
    </table>

<#include "../pageHelper2.ftl"/>
</div>
<script>
    function deleteCourse(id,name) {
        if(confirm('确定要删除' + name + '这门课程吗?')){
            window.location='${rc.contextPath}/courseController/deleteCourse/'+id;
        }
    }

    function addCourse() {
        window.location='${rc.contextPath}/courseController/toAddcourse';
    }
</script>

</body>
</html>