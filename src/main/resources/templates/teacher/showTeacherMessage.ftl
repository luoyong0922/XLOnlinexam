<html>
<head>
    <title>老师信息</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
<input type="hidden" name="id" value="${teacher.id }"/>
    <table class="table table-hover center-block" style="margin-top: 80px;width: 400px;">
    <tr>
        <td>教师工号</td>
        <td><input type="text" name="teacWorknum" value=" ${teacher.teacWorknum}" /></td>
    </tr>
    <tr>
        <td>密码</td>
        <td><input type="password" name="teacPassword" value="......" /></td>
    </tr>
    <tr>
        <td>姓名</td>
        <td><input type="text" name="teacName" value="${teacher.teacName}" /></td>
    </tr>
    <tr>
        <td>性别</td>
        <td>
            <input type="radio" name="gender" value="男" id="male">男
            <input type="radio" name="gender" value="女" id="female">女
        </td>
    </tr>
    <tr>
        <td>电话</td>
        <td><input type="text" name="teacPhone" value="${teacher.teacPhone}" /></td>
    </tr>
    <tr>
        <td>出生日期</td>
        <td><input type="text" id="age" name="teacBirth" value="${teacher.teacBirth?string("yyyy-MM-dd")}" /></td>
    </tr>
    <tr>
        <td>密保问题</td>
        <td><input type="text" name="teacQuestion" value="${teacher.teacQuestion}" /></td>
    </tr>
    <tr>
        <td>密保回答</td>
        <td><input type="text" name="teacKey" value="${teacher.teacKey}" /></td>
    </tr>
    <#--<tr>-->
        <#--<td colspan="2">-->
            <#--<a href="${rc.contextPath}/teacherController/toTeacherIndex">返回</a>-->
            <#--<a href="${rc.contextPath}/teacherController/toModifyMessage/${teacher.teacWorknum}">修改密码</a>-->
        <#--</td>-->
    <#--</tr>-->
</table>
</div>
<script>
    window.onload=function(){
        var gender='${teacher.teacGender}';
        if(gender==='男'){
            document.getElementById('male').checked=true;
        }else{
            document.getElementById('female').checked=true;
        }

    }

</script>
</body>
</html>