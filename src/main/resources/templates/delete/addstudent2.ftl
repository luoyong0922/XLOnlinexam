<html>
<head>
    <meta charset="UTF-8">
    <title>添加学生</title>
</head>
<body>
<form action="${rc.contextPath}/adminController/saveAdd" method="POST">
    <table>
        <tr>
            <td>学号</td>
            <td>
                <input type="text" name="stuNum" id="stuNum">
                <span id="stuNum_tip"></span>
            </td>
        </tr>

        <tr>
            <td>密码</td>
            <td><input type="password" name="stuPassword" id="stuPassword"></td>
        </tr>
        <tr>
            <td>名字</td>
            <td><input type="textr" name="stuName" id="stuName"></td>
        </tr>
        <tr>
            <td>性别</td>
            <td>
                <input type="radio" name="stuGender" id="male" value="男" checked="checked">男
                <input type="radio" name="stuGender" id="female" value="女">女
            </td>
        </tr>
        <tr>
            <td>地址</td>
            <td><input type="text" name="stuAddress" id="stuAddress"></td>
        </tr>
        <tr>
            <td>年级</td>
            <td><input type="text" name="stuGrade" id="stuGrade"></td>
        </tr>
        <tr>
            <td>班级</td>
            <td><input type="text" name="stuClass" id="stuClass"></td>
        </tr>
        <tr>
        <td>电话</td>
        <td><input type="text" name="stuPhone" id="stuPhone"></td>
    </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="保存" />
                <input type="reset" value="重置" />
            </td>
        </tr>
    </table>
</form>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script>
    $(function(){
        $('#stuNum').on('blur',function(){
            checkStuNum();
        });

        function checkStuNum(){
            var stuNum=$('#stuNum').val();
//            alert(username);
            if(stuNum.length == 0) {
                $('#stuNum_tip').html('');
                return;
            }
            var url='${rc.contextPath}/adminController/ajax';
            $.ajax({
                type:'post',
                url:url,
                dataType:'text', //指定返回对象
                data:{stuNum:stuNum},
                success:function(data){
                    console.log(data);
                    $('#stuNum_tip').html(data);
                },
                error:function(){
                    alert('发生错误了');
                }
            });
        }
    });
</script>

</body>
</html>