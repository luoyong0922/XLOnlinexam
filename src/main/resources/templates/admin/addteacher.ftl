<html>
<head>
    <meta charset="UTF-8">
    <title>添加老师</title>
    <link rel="stylesheet" href="${rc.contextPath}/static/css/bootstrap.min.css">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-3 column">
            </div>
            <div class="col-md-6 column">
                <form role="form" action="${rc.contextPath}/adminController/saveAddteac" method="POST" id="addTeac" style="margin-top:10px;">
                    <div class="form-group">
                        <label for="teacWorknum">工号</label>
                        <input type="text" name="teacWorknum" id="teacWorknum" class="form-control">
                        <span id="teacNum_tip"></span>
                    </div>
                    <div class="form-group">
                        <label for="teacPassword">密码</label>
                        <input type="text" class="form-control" id="teacPassword" name="teacPassword">
                    </div>
                    <div class="form-group">
                        <label for="teacName">姓名</label>
                        <input type="text" class="form-control" id="teacName" name="teacName"/>
                    </div>
                    <div class="form-group">
                        <label for="male">性别</label><br/>
                        <span style="font-size: 18px;">
                        <input type="radio" name="teacGender" style="margin: 10px 20px;" id="male" value="男" checked="checked">男&emsp;
                        <input type="radio" name="teacGender" style="margin: 10px 20px;" id="female" value="女">女
                        </span>
                    </div>
                    <div class="form-group">
                        <label for="teacPhone">电话</label>
                        <input type="text" class="form-control" id="teacPhone" name="teacPhone"/>
                    </div>
                    <div class="form-group">
                        <label for="teacBirth">出生日期</label>
                        <input type="date" class="form-control" id="teacBirth" name="teacBirth"/>
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
    function roy() {
        if($('#teacWorknum').val() != ''){
            $('#addTeac').submit();
        }else {
            alert("工号不能为空");
        }
    }
</script>

</body>
</html>