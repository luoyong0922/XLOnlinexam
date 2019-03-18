<html>
<head>
    <title>课程管理</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="margin-top: 10px;">
<form action="${rc.contextPath}/studentController/toSelectCourse/">
    课程名称:<input type="text" name="courseName" value="${courseName!}"/>
    授课老师：<input type="text" name="teacName" value="${teacName!}"/>
    <input type="text" name="pageIndex" id="pageIndex" value="1" style="display: none"/>
    <input type="submit" class="btn btn-info" value="搜索">
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
            <th>操作</th>
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
            <td>
                <a href="javascript:selCourse('${courseManage.id}','${courseManage.courseName}')">选课</a>
            </td>
        </tr>
    </#list>
        </tbody>
    </table>
<form id="selectCourse" style="display: none;">
    <input type="password" name="stuId" value="${Session["id"]!}">
    <input type="password" name="teacCourseId" id="teacCourseId"><br/>
</form>
    <#if (pageInfo.total > 6)>
        <#include "../pageHelper2.ftl"/>
    </#if>
</div>
<script>
    function selCourse(id,name) {
        if (confirm('确定要选修' + name + '吗?')) {
            $("#teacCourseId").val(id);
            var form = $("#selectCourse").serialize();
            $.ajax({
                type: "post",
                dataType: "json",
                contentType: "application/x-www-form-urlencoded",
                url: '${rc.contextPath}/studentController/selectCount',
                data: form,
                success: function (data) {
                    alert(data.message+"，若信息未自动加载，请手动刷新页面！");
                }
            });
        }
    }

</script>

</body>
</html>