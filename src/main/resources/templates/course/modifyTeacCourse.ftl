<html>
<head>
    <title>课程修改页面</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
<form action="${rc.contextPath}/adminController/doModifyTeacCourse" method="post">
    <input type="hidden" name="id" value="${teaccourse.id!}"/>
    <table class="table table-hover" style="margin-top: 80px;">
        <tr>
            <td>课程名称</td>
            <td>
                <select id="courseId" name="courseId" class="item-select" style="border-radius: 5px;height:34px;">
                    <#list courses as c>
                        <option value="${c.id!}" <#if c.id == teaccourse.courseId>selected</#if>>${c.courseName!}</option>
                    </#list>
                </select>
            </td>

        </tr>
        <#--<tr>
            <td>课程学分</td>
            <td><input type="number" name="courseCredit" id="courseCredit" value="${teaccourse.courseCredit!}"></td>
        </tr>-->
        <tr>
            <td>开课时间</td>
            <td><input type="date" name="startTime" value="${teaccourse.startTime?string("yyyy-MM-dd")}"/></td>
        </tr>
        <tr>
            <td>结课时间</td>
            <td><input type="date" name="endTime" value="${teaccourse.endTime?string("yyyy-MM-dd")}"/></td>
        </tr>
        <tr>
            <td>授课老师</td>
            <td>
                <select id="teacId" name="teacId" class="item-select" style="border-radius: 5px;height:34px;">
                    <#list teachers as t>
                        <option value="${t.id!}" <#if t.id == teaccourse.teacId>selected</#if>>${t.teacName!}</option>
                    </#list>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" class="btn btn-info" style="width:50%;margin-left:170px;" value="保存"/>
            </td>
        </tr>
    </table>
</form>
</div>

</body>
</html>