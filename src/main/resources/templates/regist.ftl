<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户注册</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <style>
        .progress-wrap {
            margin-top: 20px;
            padding: 0px;
            width: 156%;
            height: 100px;
        }

        .progress-wrap li {
            margin-top: 30px;
            list-style: none;
            float: left;
            width: 30%;
            height: 30px;
            position: relative;
        }

        .progress-wrap .progress-line {
            background-color: #b9b9b9;
            position: absolute;
            height: 8px;
            width: 100%;
            top: 13px;
            margin-left: 2px;
            transition: background-color 1s linear;
            -moz-transition: background-color 1s linear;
            -webkit-transition: background-color 1s linear;
            -o-transition: background-color 1s linear;
        }

        .progress-wrap .progress-content {
            position: absolute;
            text-align: center;
        }

        .progress-wrap .progress-content .progress-number {
            display: inline-block;
            *zoom:1;
            position: absolute;
            width: 30px;
            height: 30px;
            line-height: 30px;
            color: #fff;
            border-radius: 50%;
            border: 2px solid rgba(224,224,224,1);
            font-family: tahoma;
            font-weight: bold;
            font-size: 16px;
            background-color: #b9b9b9;
            box-shadow: 1px 1px 2px rgba(0,0,0,.2) inset;
            cursor: pointer;
        }

        .progress-wrap .progress-content .progress-text {
            position: absolute;
            font-size: 12px;
            width: 30px;
            top: 42px;
            color: #b9b9b9;
        }

        /*水晶按钮*/
        .download{
            display: block;
            height: 60px;
            width: 360px;
            padding:10px 10px;
            text-align: center;
            margin:20px auto;
            border-radius:50px;
            border:solid 1px #ccc;
            box-shadow:rgba(0,0,0,0.3) 2px 2px 3px;
            background:linear-gradient(to bottom,  rgba(242,246,248,1) 0%,rgba(216,225,231,1) 50%,rgba(180,200,200,1) 51%,rgba(224,239,249,1) 100%);
            text-shadow:#fff 0px 1px 0px;
            position: relative;
            text-decoration:none;
        }
        .download span{position: absolute;top:10%;left:34%;font-size: 18px;}
        .download:before{
            /*content: "i";*/
            display: block;
            width: 34px;
            height: 34px;
            border-radius:50%;
            background-color: #1B3D83;
            font:28px "Wingdings 3";
            line-height:34px;
            color:white;
            text-align:center;
            position: absolute;
            top:29%;
            right: 5%;
        }
    </style>
</head>
<body>
<div style="width:80%; margin:auto auto;">
    <ol class="progress-wrap">
        <li class="progress-one">
            <div class="progress-line"></div>
            <div class="progress-content">
                <span class="progress-number" id="start">1</span>
                <span class="progress-text">选择角色</span>
            </div>
        </li>
        <li class="progress-two">
            <div class="progress-line"></div>
            <div class="progress-content">
                <span class="progress-number" id="second">2</span>
                <span class="progress-text">填写信息</span>
            </div>
        </li>
        <li class="progress-three" style="width:auto;">
            <div class="progress-content">
                <span class="progress-number" id="end">3</span>
                <span class="progress-text">完成注册</span>
            </div>
        </li>
    </ol>
</div>

<div class="container" style="margin-top: 90px;" id="1">
    <div>
        <a id="teacher" class="download" >

     <span class="version">
	  老师<br/>
               I'm a teacher.
     </span>
        </a>
    </div>

    <div>
        <a id="student" class="download" >

     <span class="version">
	  学生<br/>
               I'm a student.
     </span>
        </a>
    </div>
</div>

<script>
    $("#teacher").click(function () {
        second();
        $("#1").css("display","none");
        $("#teac_info").css("display","block");
    });
    $("#student").click(function () {
        second();
        $("#1").css("display","none");
        $("#stu_info").css("display","block");
    });
</script>
<div class="container">
    <div id="stu_info" style="display: none;">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-3 column">
                </div>
                <div class="col-md-6 column">
                    <form role="form" action="${rc.contextPath}/registController/studentRegist" method="POST" id="addStu" style="margin-top:10px;">
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
                        <div class="form-group">
                            <label for="stuQuestion">密保问题</label>
                            <input type="text" class="form-control" id="stuQuestion" name="stuQuestion"/>
                        </div>
                        <div class="form-group">
                            <label for="stuKey">密保回答</label>
                            <input type="text" class="form-control" id="stuKey" name="stuKey"/>
                        </div>
                    </form>
                    <button type="button" class="btn btn-primary" style="width: 100%" onclick="xyp(1)">下一步</button>
                </div>
                <div class="col-md-3 column">
                </div>
            </div>
        </div>

    </div>

    <div id="teac_info" style="display: none;">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-3 column">
                </div>
                <div class="col-md-6 column">
                    <form role="form" action="${rc.contextPath}/registController/teacherRegist" method="POST" id="addTeac" style="margin-top:10px;">
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
                            <label for="tmale">性别</label><br/>
                            <span style="font-size: 18px;">
                        <input type="radio" name="teacGender" style="margin: 10px 20px;" id="tmale" value="男" checked="checked">男&emsp;
                        <input type="radio" name="teacGender" style="margin: 10px 20px;" id="tfemale" value="女">女
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
                        <div class="form-group">
                            <label for="teacQuestion">密保问题</label>
                            <input type="text" class="form-control" id="teacQuestion" name="teacQuestion"/>
                        </div>
                        <div class="form-group">
                            <label for="teacKey">密保回答</label>
                            <input type="text" class="form-control" id="teacKey" name="teacKey"/>
                        </div>
                    </form>
                    <button type="button" class="btn btn-primary" style="width: 100%" onclick="xyp(2)">下一步</button>
                </div>
                <div class="col-md-3 column">
                </div>
            </div>
        </div>
    </div>

    <div id="3" style="display: none;">

        <div>
            <input type="text" style="display: none;" id="type">
            <a onclick="roy();" class="download" style="background:linear-gradient(to bottom, rgb(210, 248, 214) 0%, rgb(107, 231, 131) 50%, rgb(115, 200, 130) 51%, rgb(98, 249, 110) 100%);">

     <span class="version" style="top:30%;color:blue;left:37%;">
	  完成注册
     </span>
            </a>
        </div>

    </div>

    <script>

        function xyp(data){
            if(data == 2){
                end();
                $("#teac_info").css("display","none");
                $("#3").css("display","block");
            }else if(data == 1){
                end();
                $("#stu_info").css("display","none");
                $("#3").css("display","block");
            }
            $("#type").val(data);
        }
        function roy() {
            var role = $("#type").val();
            if(role == 1){
                $("#addStu").submit();
            }else if(role == 2){
               $("#addTeac").submit();
            }else {
                alert("请先选择角色！");
            }
        }

    </script>
</div>

</body>
</html>
<script>

    $(function() {
        var currentColor = '#3c3';
        var unfinishedColor = '#b9b9b9';
        var finishedColor = '#85e085';

        $('#start')
                .css('background-color', currentColor)
                .next('span').css('color', currentColor)
                .parent('div').prev('div.progress-line').css('background-color', unfinishedColor)

        $('#second')
                .css('background-color', unfinishedColor)
                .next('span').css('color', unfinishedColor)
                .parent('div').prev('div.progress-line').css('background-color', unfinishedColor)

        $('#end')
                .css('background-color', unfinishedColor)
                .next('span').css('color', unfinishedColor)
    });
    var currentColor = '#3c3';
    var unfinishedColor = '#b9b9b9';
    var finishedColor = '#85e085';
    function start() {
        $('#start')
                .css('background-color', currentColor)
                .next('span').css('color', currentColor)
                .parent('div').prev('div.progress-line').css('background-color', unfinishedColor)

        $('#second')
                .css('background-color', unfinishedColor)
                .next('span').css('color', unfinishedColor)
                .parent('div').prev('div.progress-line').css('background-color', unfinishedColor)

        $('#end')
                .css('background-color', unfinishedColor)
                .next('span').css('color', unfinishedColor)
    }


    function second() {
        $('#second')
                .css('background-color', currentColor)
                .next('span').css('color', currentColor)
                .parent('div').prev('div.progress-line').css('background-color', unfinishedColor)

        $('#start')
                .css('background-color', finishedColor)
                .next('span').css('color', finishedColor)
                .parent('div').prev('div.progress-line').css('background-color', currentColor)

        $('#end')
                .css('background-color', unfinishedColor)
                .next('span').css('color', unfinishedColor)
    }

    function end() {
        $('#end')
                .css('background-color', currentColor)
                .next('span').css('color', currentColor)

        $('#start')
                .css('background-color', finishedColor)
                .next('span').css('color', finishedColor)
                .parent('div').prev('div.progress-line').css('background-color', finishedColor)

        $('#second')
                .css('background-color', finishedColor)
                .next('span').css('color', finishedColor)
                .parent('div').prev('div.progress-line').css('background-color', finishedColor)
    }
</script>
