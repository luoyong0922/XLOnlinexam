<html>
<head>
    <title>考试标准</title>
</head>
<body>
<form action="${rc.contextPath}/adminController/saveUpdate" method="post">
    <input type="hidden" name="id" value="${student.id }"/>
    <table width="400px" height="80px">
        <tr>
            <td>学号</td>
            <td><input type="text" name="stuNum" value=" ${student.stuNum}" /></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="stuPassword" value="${student.stuPassword}" /></td>
        </tr>
        <tr>
            <td>名字</td>
            <td><input type="text" name="stuName" value="${student.stuName}" /></td>
        </tr>
        <tr>
            <td>性别</td>
            <td>
                <input type="radio" name="gender" value="男" id="male"> 男
                <input type="radio" name="gender" value="女" id="female"> 女
            </td>
        </tr>
        <tr>
            <td>地址</td>
            <td><input type="text" name="stuAddress" value="${student.stuAddress}" /></td>
        </tr>
        <tr>
            <td>年级</td>
            <td>
                <input type="text" name="stuGrade" value="${student.stuGrade}" />
            </td>
        </tr>
        <tr>
            <td>班级</td>
            <td><input type="text" name="stuClass" value="${student.stuClass}" /></td>
        </tr>
        <tr>
            <td>电话</td>
            <td><input type="text" name="stuPhone" value="${student.stuPhone}" /></td>
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