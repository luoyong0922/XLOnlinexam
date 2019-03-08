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
                <form role="form" action="${rc.contextPath}/adminController/saveUpdate" method="post" id="modifyStu" style="margin-top:10px;">
                    <input type="text" style="display: none;" name="id" value="${student.id!}"/>
                    <div class="form-group">
                        <label for="stuNum">学号</label>
                        <input type="text" name="stuNum" id="stuNum" value="${student.stuNum!}" class="form-control">
                        <span id="stuNum_tip"></span>
                    </div>
                    <div class="form-group">
                        <label for="stuPassword">密码</label>
                        <input type="text" class="form-control" id="stuPassword" name="stuPassword" value="${student.stuPassword!}">
                    </div>
                    <div class="form-group">
                        <label for="stuName">姓名</label>
                        <input type="text" class="form-control" id="stuName" name="stuName" value="${student.stuName!}"/>
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
                        <input type="text" class="form-control" id="stuAddress" name="stuAddress" value="${student.stuAddress!}"/>
                    </div>
                    <div class="form-group">
                        <label for="stuGrade">年级</label>
                        <input type="text" class="form-control" id="stuGrade" name="stuGrade" value="${student.stuGrade!}"/>
                    </div>
                    <div class="form-group">
                        <label for="stuClass">班级</label>
                        <input type="text" class="form-control" id="stuClass" name="stuClass" value="${student.stuClass!}"/>
                    </div>
                    <div class="form-group">
                        <label for="stuPhone">电话</label>
                        <input type="text" class="form-control" id="stuPhone" name="stuPhone" value="${student.stuPhone!}"/>
                    </div>

                    <button type="button" class="btn btn-primary" style="width: 100%" onclick="roy()">保存修改</button>
                </form>
            </div>
            <div class="col-md-3 column">
            </div>
        </div>
    </div>
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
    function roy() {
        if($('#stuNum').val() != ''){
            $('#modifyStu').submit();
        }else {
            alert("学号不能为空");
        }
    }
</script>

</body>
</html>