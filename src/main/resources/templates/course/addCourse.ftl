<html>
<head>
    <title>添加课程</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
<form action="${rc.contextPath}/courseController/doAddCourse" method="POST">
    <table class="table table-hover" style="margin-top: 80px;">
        <tr>
            <td>课程编号</td>
            <td>
                <input type="text" name="courseNum" id="courseNum">
            </td>
        </tr>
        <tr>
            <td>课程名称</td>
            <td><input type="text" name="courseName" id="courseName"></td>
        </tr>
        <tr>
            <td>课程学分</td>
            <td><input type="number" name="courseCredit" id="courseCredit"></td>
        </tr>
        <tr>
            <td>开课时间</td>
            <td><input type="date" name="startTime"/></td>
        </tr>
        <tr>
            <td>结课时间</td>
            <td><input type="date" name="endTime"/></td>
        </tr>
        <tr>
            <td>授课老师</td>
            <td>
                <select id="teacId" name="teacId" class="item-select" style="border-radius: 5px;height:34px;">
                <#list teachers as t>
                    <option value="${t.id!}">${t.teacName!}</option>
                </#list>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" class="btn btn-info center-block" style="width:45%;" value="保存" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>