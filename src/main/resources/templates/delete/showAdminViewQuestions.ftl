<html>
<head>
    <title>试题管理</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<form action="${rc.contextPath}/paperController/getMyQuestions" method="post">
    课程名称:<input type="text" name="courseName" value="${courseName}"/>

    试题类型：
    <select name="questionType" id="questionType">
    <#-- // 1：多选题，2：单选题，3:判断题，4：填空题，5：计算题，6：主观题-->
        <option value="1">多选题</option>
        <option value="2">单选题</option>
        <option value="3">判断题</option>
        <option value="4">填空题</option>
        <option value="5">计算题</option>
        <option value="6">主观题</option>
    </select>
    <input type="text" name="pageIndex" id="pageIndex" value="1" style="display: none"/>
    <input type="submit" value="搜索">
</form>
<form method="post">
    <table width="300px" border="1px" cellspacing="0" id="Test">
        <tr>
            <th>序号</th>
            <th>课程名称</th>
            <th>题干</th>
            <th>难易程度</th>
            <th>试题类型</th>
            <th>操作</th>
        </tr>
    <#list pageInfo.list as adminViewQuestion>
        <tr>
            <td >${adminViewQuestion?counter}</td>
            <td>${adminViewQuestion.courseName}</td>
            <td>${adminViewQuestion.title}</td>
            <td>${adminViewQuestion.dificult}</td>
            <td>${adminViewQuestion.questionType}</td>
            <td>
                <!-- 按钮：用于打开模态框 -->
                <button type="button" data-toggle="modal" data-target="#ShowQuestion"
                        onclick="toShowQuestion(${adminViewQuestion.id},'${adminViewQuestion.questionType}')">
                    查看详情</button>
            </td>
        </tr>
    </#list>
    </table>
</form>

<!-- 查看试题模态框 -->

<div class="modal fade" id="ShowQuestion" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
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
                        <label for="username" class="col-sm-2 control-label">
                            题干</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="title" name="title" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label">
                            参考答案：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="answer" name="answer" />
                            <input type="text" class="form-control" id="examId" name="examId" style="display: none;"/>
                        </div>
                    </div>
                    <div class="form-group" id="t1">
                        <label for="password" class="col-sm-2 control-label">A选项</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="type1" name="type1" />
                        </div>
                    </div>
                    <div class="form-group" id="t2">
                        <label for="phone" class="col-sm-2 control-label">B选项</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="type2" name="type2" />
                        </div>
                    </div>
                    <div class="form-group" id="t3">
                        <label for="gender" class="col-sm-2 control-label">C选项</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="type3" name="type3" />
                        </div>
                    </div>
                    <div class="form-group" id="t4">
                        <label for="idcard" class="col-sm-2 control-label">D选项</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="type4" name="type4" />
                        </div>
                    </div>
                    <div class="form-group" id="t5">
                        <label for="idcard" class="col-sm-2 control-label">E选项</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="type5" name="type5" />
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
                    <#--分页 start-->
                    <#include "../pageHelper2.ftl"/>
                    <#--分页 end-->

                    <script type="text/javascript">
                        // 通过id获取修改的客户信息
                        function toShowQuestion(title,questionType) {
                            var tre = {};
                            tre.id = title;
                            tre.questionType = questionType;
                            var data = JSON.stringify(tre);
                            $.ajax({
                                type:"post",
                                dataType: "json",
                                contentType:"application/json",
                                url:"${rc.contextPath}/getQuestionAjax",
                                data :data,
                                success:function(data) {
                                    console.log(data);
                                    // 1：多选题，2：单选题，3:判断题，4：填空题，5：计算题，6：主观题
                                    $("#title").val(data.title);
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