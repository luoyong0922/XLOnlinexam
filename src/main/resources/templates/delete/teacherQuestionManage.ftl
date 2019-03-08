<html>
<head>
    <title>题库管理</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<form action="${rc.contextPath}/paperController/getMyQuestions" method="post">
    <input type="text" name="pageIndex" id="pageIndex" value="1" style="display: none"/>
    课程名称:<input type="text" name="courseName" value="${courseName!}"/>
    试题类型：
    <select name="questionType" id="questionType">
    <#-- // 1：多选题，2：单选题，3:判断题，4：填空题，5：计算题，6：主观题-->
        <option value="1">多选题</option>
        <option value="2">单选题</option>
        <option value="3">判断题</option>
        <option value="4">填空题</option>
        <option value="5">计算题</option>
        <option value="6">主观题</option>
    </select>
    <input type="text" name="id" value="${Session["id"]!}" style="display: none"/>
    <input type="submit" value="搜索">
</form>
<form method="post">
    <table width="300px" border="1px" cellspacing="0" id="Test">
        <tr>
            <th>序号</th>
            <th>课程名称</th>
            <th>题干</th>
            <th>难易程度</th>
            <th>题目类型</th>
            <th colspan="3">操作</th>
        </tr>
    <#list pageInfo.list as adminViewQuestion>
        <tr>courses
            <td >${adminViewQuestion?counter}</td>
            <td>${adminViewQuestion.courseName}</td>
            <td>${adminViewQuestion.title}</td>
            <td>${adminViewQuestion.dificult}</td>
            <td>${adminViewQuestion.questionType}</td>

            <td>
                <a>修改</a>
                <a>删除</a>
                <a>查看详情</a>
            </td>
        </tr>
    </#list>
    </table>
</form>
<#--分页 start-->
                    <#include "../pageHelper2.ftl"/>
<#--分页 end-->

</body>
</html>