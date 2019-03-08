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
                <form role="form" action="${rc.contextPath}/adminController/saveTeacUpdate" method="POST" id="modifyTeac" style="margin-top:10px;">
                    <input type="text" style="display: none;" name="id" value="${teacher.id!}"/>
                    <div class="form-group">
                        <label for="teacWorknum">工号</label>
                        <input type="text" name="teacWorknum" id="teacWorknum" class="form-control" value="${teacher.teacWorknum!}">
                        <span id="teacNum_tip"></span>
                    </div>
                    <div class="form-group">
                        <label for="teacPassword">密码</label>
                        <input type="text" class="form-control" id="teacPassword" name="teacPassword" value="${teacher.teacPassword!}">
                    </div>
                    <div class="form-group">
                        <label for="teacName">姓名</label>
                        <input type="text" class="form-control" id="teacName" name="teacName" value="${teacher.teacName!}"/>
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
                        <input type="text" class="form-control" id="teacPhone" name="teacPhone" value="${teacher.teacPhone!}"/>
                    </div>
                    <div class="form-group">
                        <label for="teacBirth">出生日期</label>
                        <input type="date" class="form-control" id="teacBirth" name="teacBirth" value="${teacher.teacBirth?string("yyyy-MM-dd")}"/>
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
        if($('#teacWorknum').val() != ''){
            $('#addTeac').submit();
        }else {
            alert("工号不能为空");
        }
    }
</script>

</body>
</html>