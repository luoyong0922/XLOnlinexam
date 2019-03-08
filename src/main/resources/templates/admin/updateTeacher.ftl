<html>
<head>
    <title>教师修改页面</title>
</head>
<body>
<form action="${rc.contextPath}/adminController/saveTeacUpdate" method="post">
    <input type="hidden" name="id" value="${teacher.id }"/>
    <table width="400px" height="80px">
        <tr>
            <td>工号</td>
            <td><input type="text" name="teacWorknum" value="${teacher.teacWorknum}" /></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="teacPassword" value="${teacher.teacPassword}" /></td>
        </tr>
        <tr>
            <td>名字</td>
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
            <td>出生年月</td>
            <td><input type="text" id="age" name="teacBirth" value="${teacher.teacBirth?string("yyyy-MM-dd")}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="保存" />
                <input type="reset" value="重置" />
            </td>
        </tr>
    </table>
</form>
<script>
    window.onload=function(){
        var gender='${student.gender}';
        if(gender==='男'){
            document.getElementById('male').checked=true;
        }else{
            document.getElementById('female').checked=true;
        }

    }

</script>
</body>
</html>