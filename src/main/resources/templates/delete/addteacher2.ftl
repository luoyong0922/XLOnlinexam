<html>
<head>
    <meta charset="UTF-8">
    <title>添加老师</title>
    <link rel="stylesheet" href="${rc.contextPath}/static/css/bootstrap.min.css">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
</head>
<body>
<form action="${rc.contextPath}/adminController/saveAddteac" method="POST">
    <table>
        <tr>
            <td>工号</td>
            <td>
                <input type="text" name="teacWorknum" id="teacWorknum">
                <span id="teacNum_tip"></span>
            </td>
        </tr>

        <tr>
            <td>密码</td>
            <td><input type="text" name="teacPassword" id="teacPassword"></td>
        </tr>
        <tr>
            <td>名字</td>
            <td><input type="text" name="teacName" id="teacName"></td>
        </tr>
        <tr>
            <td>性别</td>
            <td>
                <input type="radio" name="teacGender" id="male" value="男" checked="checked">男
                <input type="radio" name="teacGender" id="female" value="女">女
            </td>
        </tr>
        <tr>
        <td>电话</td>
        <td><input type="text" name="teacPhone" id="teacPhone"></td>
    </tr>
        <td>出生年月</td>
        <td><input type="text" name="teacBirth" id="teacBirth"></td>
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
        $('#teacWorknum').on('blur',function(){
            checkteacNum();
        });

        function checkteacNum(){
            var teacWorknum=$('#teacWorknum').val();
//            alert(username);
            if(teacWorknum.length == 0) {
                $('#teacNum_tip').html('');
                return;
            }
            var url='${rc.contextPath}/adminController/teacajax';
            $.ajax({
                type:'post',
                url:url,
                dataType:'text', //指定返回对象
                data:{teacWorknum:teacWorknum},
                success:function(data){
                    console.log(data);
                    $('#teacNum_tip').html(data);
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