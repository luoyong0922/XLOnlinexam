<html>
<head>
    <title>考试标准</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container center-block" style="margin-top: 50px;">
    <table class="table">

        <tr>
            <td>课程名称</td>
            <td>
                <select id="select" name="id" class="item-select" style="border-radius: 5px;height:34px;">
                    <#list teacCourses as teacCourse>
                        <option value="${teacCourse.id}"  <#if teacCourse.id == teacCourseId>selected</#if>>${teacCourse.courseName}</option>
                    </#list>
                </select>
            </td>
        </tr>

        <tr>
            <td>考试时长</td>
            <td><input type="number" name="testTime" id="d2">分钟</td>
        </tr>
    </table>

    <h3>试题模块：</h3>
    <table class="table table-hover" id="Test">
        <thead>
        <tr>
            <th>题型</th>
            <th>题量</th>
            <th>分值</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input  type="text" id="d3" value="单选题" readonly></td>
            <td><input type="number" id="d4"></td>
            <td><input type="text" id="d5"></td>
        </tr>
        <tr>
            <td><input  type="text" id="d6" value="多选题" readonly></td>
            <td><input type="number" id="d7"></td>
            <td><input type="text" id="d8"></td>
        </tr>
        <tr>
            <td><input  type="text" id="d9" value="判断题" readonly></td>
            <td><input type="number" id="d10"></td>
            <td><input type="text" id="d11"></td>
        </tr>
        <tr>
            <td><input  type="text"id="d12" value="填空题" readonly></td>
            <td><input type="number" id="d13"></td>
            <td><input type="text" id="d14"></td>
        </tr>
        <tr>
            <td><input  type="text"id="d15" value="计算题" readonly></td>
            <td><input type="number" id="d16"></td>
            <td><input type="text" id="d17"></td>
        </tr>
        <tr>
            <td><input  type="text" id="d18" value="主观题" readonly></td>
            <td><input type="number" id="d19"></td>
            <td><input type="text" id="d20"></td>
        </tr>
        </tbody>
    </table>
     <input type="button" class="btn btn-info" value="发布考试" id="publishpaper"/>
    <div id="loading" style="display: none;"></div>
    <script>
        $('#publishpaper').click(function () {
            var $teacCourseId = $('#select').val();
            if($teacCourseId == null){
                alert("请选择课程");
            }else {
                var formdata = $teacCourseId + ",";
                var time = $(':input[id ^="d"]');
                time.each(function () {
                    if ($(this).val() != null && "" != $(this).val()) {
                        formdata += $(this).val() + ",";
                    } else {
                        formdata += "#,";
                    }
                })
                //teaccourseid,testTime,.........
                formdata = formdata.substring(0, formdata.length - 1);
                $.ajax({
                    url: '${rc.contextPath}/paperController/doPublicPaper',
                    type: "POST",
                    dataType: "json",
                    data: formdata,
                    contentType: "application/json",
                    beforeSend: function () {
                        $("#loading").text("正在提交...");
                        $("#loading").show();
                    },
                    success: function (data) {
                        if (data.code == "success") {
                            $("#loading").hide();
                            alert("保存成功!");
                            window.location = "${rc.contextPath}/courseController/getCourseMessage?role=2";
                        } else {
                            $("#loading").hide();
                            alert(data.message);

                        }
                    },
                    error:function () {
                        alert("找不到服务器");
                    }
                });
            }
        });
    </script>

</body>
</html>