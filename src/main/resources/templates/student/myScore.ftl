<html>
<head>
    <title>学生试卷列表</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${rc.contextPath}/static/css/bootstrap.min.css">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <style>
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

<div class="container">

    <h2 style="text-align: center">试卷列表</h2><br/>

    <form action="${rc.contextPath}/studentController/getStuScore?pageIndex=1" method="post" id="search">
        课程名称：
        <select name="teacCourseId" id="selectValue" class="select-control">
            <option value="0">--请选择课程--</option>
            <#list teacCourses as teacCourse>
                <option value="${teacCourse.id}">${teacCourse.courseName}</option>
            </#list>
        </select>
        <input type="button" class="btn btn-primary" value="搜索" onclick="javascript:roy()">&emsp;&emsp;&emsp;
    </form>
    <script>
        function roy(){

            if($('#selectValue option:selected').val() == 0){
                alert('你还未选择课程！');
            }else {
                $("#search").submit();
            }
        }
    </script>

    <div class="container">

        <table class="table table-hover" id="Test">
            <thead>
            <tr>
                <th>序号</th>
                <th>课程名称</th>
                <th>授课老师</th>
                <th>测评单元</th>
                <th>测评周次</th>
                <th>测评用时</th>
                <th>成绩</th>
                <th>状态</th>
                <th colspan="2" style="text-align:center;">操作</th>
            </tr>
            </thead>
            <tbody>
            <#if (pageInfo.total > 0)>
    <#list pageInfo.list as stuScore>
    <tr>
        <td>${stuScore?counter}</td>
        <td>${stuScore.courseName}</td>
        <td>${stuScore.teacName}</td>
        <td>单元${stuScore.testUnit}</td>
        <td>测评${stuScore.testNum}</td>
        <td>${stuScore.testTime} min</td>
        <td>${stuScore.score}</td>
        <td>
          <#if stuScore.paperStatus==0>待批阅
          <#elseif stuScore.paperStatus==1>已批阅
          </#if>
        </td>
        <td style="text-align:center;">
            <a href="javascript:void(0);" onclick="toShowPaper(${stuScore.paperId},'${stuScore.courseName}',${stuScore.testTime})" >查看试题</a>&emsp;
            <a href="javascript:void(0);" onclick="toScoreOrder(${stuScore.teacCourseId},'${stuScore.courseName}')">查看排名</a>
        </td>
    </tr>
    </#list>
    <#else>
    <tr style="text-align:center">暂无数据</tr>
    </#if>
            </tbody>
        </table>
    </div>
    <#if (pageInfo.total > 6)>
        <#include "../pageHelper2.ftl"/>
    </#if>
</div>

<script>
    function toScoreOrder(teacCourseId , courseName) {
            window.location = '${rc.contextPath}/achievementController/showGradeOrder/' + courseName + '?teacCourseId=' + teacCourseId;
    }
    function toShowPaper(paperId , courseName, testTime) {
            window.location = '${rc.contextPath}/paperController/toMarking/'+paperId+'?courseName='+ courseName + '&testTime=' + testTime;
        }
</script>
</body>
</html>