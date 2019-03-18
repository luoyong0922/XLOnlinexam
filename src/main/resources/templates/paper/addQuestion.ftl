<html>
<head>
    <title>试题管理</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${rc.contextPath}/static/css/radio.css" rel="stylesheet">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
    <style>
        #rad{
            vertical-align: -2px;
            font-size:20px;
        }
        .regular-radio{
            vertical-align:middle; margin-top:-2px; margin-bottom:1px;
        }
        .select-control{
            /*display: block;*/
            width: 150px;
            height: 34px;
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            color: #555;
            background-color: #fff;
            background-image: none;
            border: 1px solid #ccc;
            border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
            -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
            transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        }
    </style>
</head>
<body>
<div class="container" style="margin-top: 20px;">
<form action="${rc.contextPath}/paperController/getMyQuestions" method="post" id="search">
    课程名称：
    <select name="teacCourseId" id="selectValue" class="select-control" >
        <option value="0">--请选择课程--</option>
<#list teacCourses as teacCourse>
    <option class="" value="${teacCourse.id}" <#if teacCourse.id == teacCourseId>selected</#if>>${teacCourse.courseName}</option>
</#list>
</select>
    <input name="courseName" id="coursename" style="display: none;">
    试题类型：
    <select name="questionType" id="questionType"  class="select-control" style="width:135px">
    <#-- // 1：多选题，2：单选题，3:判断题，4：填空题，5：计算题，6：主观题-->
        <option value="0">--请选择题型--</option>
        <option value="1">多选题</option>
        <option value="2">单选题</option>
        <option value="3">判断题</option>
        <option value="4">填空题</option>
        <option value="5">计算题</option>
        <option value="6">主观题</option>
    </select>
    所属单元：<input type="number" id="testUnit" name="dificult" value="${testUnit!}"  class="select-control" style="width:80px"/>
    <input type="button" class="btn btn-primary" value="搜索" onclick="javascript:xyp()">
</form>
<script>
    function xyp(){

        if($('#questionType option:selected').val() == 0 || $('#selectValue option:selected').val() == 0){
            alert('你还未选择课程名称或题型！');
        }else {
            $("#coursename").val($('#selectValue option:selected')[0].text);
            $("#search").submit();
        }
    }
</script>
<!-- 按钮：用于打开 添加试题 模态框 -->
<button type="button" class="btn btn-success" onclick="addQuestion()" style="float: right;margin-top: -47px;margin-right: 48px;">添加试题</button>

    <table class="table table-hover" id="Test">
        <thead>
        <tr>
            <th>序号</th>
            <th>课程名称</th>
            <th>题干</th>
            <th>授课老师</th>
            <th>所属单元</th>
            <th>试题类型</th>
            <th style="text-align: center;">操作</th>
        </tr>
        </thead>
        <tbody>
        <#if (pageInfo.size > 0)>
    <#list pageInfo.list as adminViewQuestion>

        <tr>
            <td >${adminViewQuestion?counter}</td>
            <td>${adminViewQuestion.courseName}</td>
            <td>${adminViewQuestion.title}</td>
            <td><#if Session["name"]?exists>${Session["name"]}</#if></td>
            <td>${adminViewQuestion.dificult}</td>
            <td>${adminViewQuestion.questionType}</td>
            <td style="text-align: center;">
                <input type="button" class="btn btn-info" value="查看详情"
                        onclick="toShowQuestion(${adminViewQuestion.id},'${adminViewQuestion.questionType}')"/>
                <input type="button" class="btn btn-danger" value=" 删除试题"
                       onclick="toDel(${adminViewQuestion.id},'${adminViewQuestion.questionType}')"/>

            </td>
        </tr>
    </#list>
        <#else >
        <tr><td colspan="6" style="text-align: center;">暂无数据</td></tr>
        </#if>
        </tbody>
    </table>

<!-- 添加试题模态框 -->
<div class="modal fade" id="addQuestion" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="z-index: inherit;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">题目</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="add" action="" method="post" style="line-height: 1em;">
                    <input type="text"  name="examId" style="display: none;" id="ei"/>
                    <input type="text"  name="teacCourseId" style="display: none;" id="tci"/>
                    <div class="form-group">
                        <label for="username" class="col-sm-4 control-label">
                            题干</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="title" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username" class="col-sm-4 control-label ">所属单元</label>
                        <div class="col-sm-8">
                            <input type="number" class="form-control" name="dificult" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username" class="col-sm-4 control-label">
                            参考答案(选项）：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="answer" id="aanswer"/>
                        </div>
                    </div>
                    <div class="form-group" id="a1">
                        <label for="password" class="col-sm-4 control-label">A</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"  name="type1" />
                        </div>
                    </div>
                    <div class="form-group" id="a2">
                        <label for="phone" class="col-sm-4 control-label">B</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"  name="type2" />
                        </div>
                    </div>
                    <div class="form-group" id="a3">
                        <label for="gender" class="col-sm-4 control-label">C</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"  name="type3" />
                        </div>
                    </div>
                    <div class="form-group" id="a4">
                        <label for="idcard" class="col-sm-4 control-label">D</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="type4" />
                        </div>
                    </div>
                    <div class="form-group" id="a5">
                        <label for="idcard" class="col-sm-4 control-label">E</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="type5" />
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary center-block" style="width:80%;">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- 查看试题模态框 -->

<div class="modal fade" id="ShowQuestion" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="z-index: inherit;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">题目</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="user">
                    <div class="form-group">
                        <label for="username" class="col-sm-4 control-label">
                            题干</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="title" name="title" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username" class="col-sm-4 control-label">
                            参考答案：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="answer" name="answer" />
                            <input type="text" class="form-control" id="examId" name="examId" style="display: none;"/>
                        </div>
                    </div>
                    <div class="form-group" id="t1">
                        <label for="password" class="col-sm-4 control-label">A</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="type1" name="type1" />
                        </div>
                    </div>
                    <div class="form-group" id="t2">
                        <label for="phone" class="col-sm-4 control-label">B</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="type2" name="type2" />
                        </div>
                    </div>
                    <div class="form-group" id="t3">
                        <label for="gender" class="col-sm-4 control-label">C</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="type3" name="type3" />
                        </div>
                    </div>
                    <div class="form-group" id="t4">
                        <label for="idcard" class="col-sm-4 control-label">D</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="type4" name="type4" />
                        </div>
                    </div>
                    <div class="form-group" id="t5">
                        <label for="idcard" class="col-sm-4 control-label">E</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="type5" name="type5" />
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<#if (pageInfo.total > 6)>
        <#include "../pageHelper2.ftl"/>
</#if>
</div>
<script type="text/javascript">
    //添加试题
    function  addQuestion() {

        // 1：多选题，2：单选题，3:判断题，4：填空题，5：计算题，6：主观题
        var type = $('#questionType option:selected').val();
        var courseName = $('#selectValue option:selected').val();
        if (type == 0 || courseName == 0) {
            alert('你还未选择课程或题型！');
        } else {
            $("#addQuestion").modal("show");
            $("#ei").val(type);
            $("#tci").val($('#selectValue option:selected').val());
            if (type == 1) {//多选题
                $("#add").attr("action", "${rc.contextPath}/teacherController/addMulSel");
            }
            if (type == 2) {//单选题
                $("#add").attr("action", "${rc.contextPath}/teacherController/addSel");
                $("#a5").hide();
            }
            if (type == 3) {//判断题
                $("#a3").hide();
                $("#a4").hide();
                $("#a5").hide();
                $("#add").attr("action", "${rc.contextPath}/teacherController/addJudge");
            }
            //判断题，计算题，主观题
            if (type == 4 || type == 5 || type == 6) {
                $("#a1").hide();
                $("#a2").hide();
                $("#a3").hide();
                $("#a4").hide();
                $("#a5").hide();

            }
            if (type == 4 || type == 5) {
                $("#aanswer").attr("name", "answer1");
            }
            if (type == 4) {//填空题
                $("#add").attr("action", "${rc.contextPath}/teacherController/addFill");
            }
            if (type == 5) {//计算题
                $("#add").attr("action", "${rc.contextPath}/teacherController/addCal");
            }
            if (type == 6) {//主观题
                $("#add").attr("action", "${rc.contextPath}/teacherController/addSubject");
            }
        }
    }
    // 通过id删除试题
    function toDel(questionId,questionType) {
        var tre = {};
        tre.id = questionId;
        tre.questionType = questionType;
        var data = JSON.stringify(tre);
        $.ajax({
            type: "post",
            dataType: "json",
            contentType: "application/json",
            url: "${rc.contextPath}/paperController/deleteQuestion",
            data: data,
            success: function (data) {
                alert(data.message);
                window.location='${rc.contextPath}/paperController/getMyQuestions?id=${Session["id"]!}';
            },
            error: function () {
                alert("出错了");
            }
        });
    }
    // 通过id获取试题信息
    function toShowQuestion(questionId,questionType) {
        $("#ShowQuestion").modal("show");
        var tre = {};
        tre.id = questionId;
        tre.questionType = questionType;
        var data = JSON.stringify(tre);
        $.ajax({
            type:"post",
            dataType: "json",
            contentType:"application/json",
            url:"${rc.contextPath}/paperController/getQuestionAjax",
            data :data,
            success:function(data) {
                console.log(data);
                // 1：多选题，2：单选题，3:判断题，4：填空题，5：计算题，6：主观题
                $("#title").val(data['title']);
                $("#examId").val(data.examId);
                if(data.examId == 1 || data.examId == 2){
                    $("#answer").val(data.answer);
                    $("#type1").val(data.type1);
                    $("#type2").val(data.type2);
                    $("#type3").val(data.type3);
                    $("#type4").val(data.type4);
                }
                if(data.examId == 1){//多选题
                    $("#type5").val(data.type5);
                }
                if(data.examId == 2){//单选题
                    $("#t5").hide();
                }
                if(data.examId == 3){//判断题
                    $("#answer").val(data.answer);
                    $("#type1").val(data.type1);
                    $("#type2").val(data.type2);
                    $("#t3").hide();
                    $("#t4").hide();
                    $("#t5").hide();
                }
                //判断题，计算题，主观题
                if(data.examId == 4 || data.examId == 5 || data.examId == 6){
                    $("#t1").hide();
                    $("#t2").hide();
                    $("#t3").hide();
                    $("#t4").hide();
                    $("#t5").hide();
                }
                if(data.examId == 4 || data.examId == 5 ){
                    $("#answer").attr("name","answer1");
                    $("#answer").val(data.answer1);
                }
                if(data.examId == 6 ){//主观题
                    $("#answer").val(data.answer);
                }
            },
            error:function(){
                alert("出错了");
            }
        });
    }
</script>

</body>
</html>