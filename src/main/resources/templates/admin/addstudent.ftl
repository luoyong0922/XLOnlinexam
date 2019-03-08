<html>
<head>
    <meta charset="UTF-8">
    <title>添加学生</title>
    <link rel="stylesheet" href="${rc.contextPath}/static/css/bootstrap.min.css">
    <link href="${rc.contextPath}/static/css/radio.css" rel="stylesheet">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-3 column">
        </div>
        <div class="col-md-6 column">
            <form role="form" action="${rc.contextPath}/adminController/saveAdd" method="POST" id="addStu" style="margin-top:10px;">
                <div class="form-group">
                    <label for="stuNum">学号</label>
                    <input type="text" name="stuNum" id="stuNum" class="form-control">
                    <span id="stuNum_tip"></span>
                </div>
                <div class="form-group">
                    <label for="stuPassword">密码</label>
                    <input type="password" class="form-control" id="stuPassword" name="stuPassword">
                </div>
                <div class="form-group">
                    <label for="stuName">姓名</label>
                    <input type="text" class="form-control" id="stuName" name="stuName"/>
                </div>
                <div class="form-group">
                    <label for="male">性别</label><br/>
                    <span style="font-size: 18px;">
                        <input type="radio" name="stuGender" style="margin: 10px 20px;" id="male" value="男" checked="checked">男&emsp;
                        <input type="radio" name="stuGender" style="margin: 10px 20px;" id="female" value="女">女
                        </span>
                </div>
                <div class="form-group">
                    <label for="stuAddress">住址</label>
                    <input type="text" class="form-control" id="stuAddress" name="stuAddress"/>
                </div>
                <div class="form-group">
                    <label for="stuGrade">年级</label>
                    <input type="text" class="form-control" id="stuGrade" name="stuGrade"/>
                </div>
                <div class="form-group">
                    <label for="stuClass">班级</label>
                    <input type="text" class="form-control" id="stuClass" name="stuClass"/>
                </div>
                <div class="form-group">
                    <label for="stuPhone">电话</label>
                    <input type="text" class="form-control" id="stuPhone" name="stuPhone"/>
                </div>

                <button type="button" class="btn btn-primary" style="width: 100%" onclick="roy()">提交</button>
            </form>
        </div>
        <div class="col-md-3 column">
        </div>
    </div>
</div>
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
    function roy() {
        if($('#stuNum').val() != ''){
            $('#addStu').submit();
        }else {
            alert("学号不能为空");
        }
    }
</script>

</body>
</html>