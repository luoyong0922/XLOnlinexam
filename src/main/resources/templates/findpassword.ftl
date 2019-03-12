<html>
<head>
    <title>找回密码</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="center-block" style="width:240px;margin-top:170px;">
    <div class="form-group">
        <label for="account">账号：</label>
        <input type="text" name="account" id="account">
    </div>
    <div class="form-group">
        <label for="role">角色：&emsp;&emsp;&emsp;</label>
        <select id="role" name="role" class="item-select" style="border-radius: 5px;height:34px;">
            <option value="1" >学生</option>
            <option value="2">教师</option>
            <option value="3">管理员</option>
        </select>
    </div>

    <button type="button" class="btn btn-primary"  onclick="javascript:window.location.href='${rc.contextPath}/toIndex'">返回首页</button>
    <!-- 按钮：用于打开模态框 -->
    <button type="button" class="btn btn-primary" style="float: right;" onclick="search(0)">
        找回密码</button>
</div>



<!-- 找回密码模态框 begin-->
<div class="modal fade" id="findPwd" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" id="dosearch">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">找回密码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal"  action="" method="post">
                    <div class="form-group">
                        <label for="question" class="col-sm-4 control-label">
                            密保问题：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="question" name="question" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="answer" class="col-sm-4 control-label">
                            问题答案：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="answer" name="answer" />
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary center-block" onclick="search(1)">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- 找回密码模态框 end-->
</div>
<script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
<script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
<script>
    // 通过account,role获取信息
    function search(opration) {
        if ($("#account").val() == '') {
            alert("请输入账号！");
        } else {
            var tre = {};
            tre.account = $("#account").val();
            tre.role = $('#role option:selected').val();
            tre.opration = opration;
            tre.answer = $("#answer").val();
            if(opration == 1 && $("#answer").val() == ''){
                alert("请输入问题答案！");
            }else {
                var data = JSON.stringify(tre);
                $.ajax({
                    type: "POST",
                    contentType: 'application/json;charset=utf-8',
                    dataType: 'json',
                    url: "${rc.contextPath}/loginController/doFindPassword",
                    data: data,
                    success: function (data) {
                        if (data.code == "success") {//密保问题查询成功
                            $('#findPwd').modal('show');
                            $("#dosearch").attr("style", "z-index: inherit;");
                            $("#question").val(data.message);
                        } else if (data.code == "ok") {//密码查询成功
                            alert("你的密码已被初始化为：123456，为了你的信息安全着想，登录后请尽快修改你的初始密码。");
                            window.location = '${rc.contextPath}/loginController/tologin';
                        } else if (data.code == "fail") {
                            alert(data.message);
                        }
                    },
                    error: function () {
                        alert("出错了");
                    }
                });
            }
        }
    }
</script>

<body>
</html>
