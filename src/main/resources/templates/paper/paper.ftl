<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>开始考试</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<style>
    .xyp{
        font-size: 20px;
        background-color: #d9edf7;
        width: 40px;
        height: 30px;
        display: initial;
    }

</style>

<body style="background-color: #FFFFE9;">
<div class="content" id="" style="width:80%;margin:auto auto;">

    <div class="">
        <h1><strong>${courseName!}</strong>考试</h1>
        <h3 style="float: right;">考试总时长<span id="testtime">${testTime}</span></h3><br/><br/>
        考试剩余时长：<div id="time" class="xyp"></div>分钟
    </div>

<div id="question_box">

    <!-- Append question's html code here. -->
    <!----------------------------- 单选题 -->
<#if selects?? && (selects?size > 0) >
    <h3>单项选择题</h3>
<div class="" name="" id="">
    <#list selects as item>
        <div class="title">
            <span class="">${item?counter}.</span>
        ${item.title!}<br/>
        </div>
        <div class=" " style="">
            <input type="radio" name="as${item?counter}" id="" class=" " value="A"/>
            <label class=" " style="cursor: pointer;">A.${item.type1!}</label><br/>
            <input type="radio" name="as${item?counter}" id="" class=" " value="B"/>
            <label class=" " style="cursor: pointer;">B.${item.type2!}</label><br/>
            <input type="radio" name="as${item?counter}" id="" class=" " value="C"/>
            <label class=" " style="cursor: pointer;">C.${item.type3!}</label><br/>
            <input type="radio" name="as${item?counter}" id="" class=" " value="D"/>
            <label class=" " style="cursor: pointer;">D.${item.type4!}</label><br/>
        </div>
    </#list>
</#if>
<#if multiSelects?? && (multiSelects?size > 0) >
    <!----------------------------------------------- 多选题 -->
    <h3>多项选择题</h3>
    <div class="" name="" id="">
        <#list multiSelects as item>
            <div class="title">
                <span class="">${item?counter}.</span>
            ${item.title!}<br/>
            </div>
            <div class=" " style="">
                <input type="checkbox" name="am${item?counter}" id="" class=" " value="A"/>
                <label class=" " style="cursor: pointer;">A.${item.type1!}</label><br/>
                    <input type="checkbox" name="am${item?counter}" id="" class=" " value="B"/>
                <label class=" " style="cursor: pointer;">B.${item.type2!}</label><br/>
                <input type="checkbox" name="am${item?counter}" id="" class=" " value="C"/>
                <label class=" " style="cursor: pointer;">C.${item.type3!}</label><br/>
                <input type="checkbox" name="am${item?counter}" id="" class=" " value="D"/>
                <label class=" " style="cursor: pointer;">D.${item.type4!}</label><br/>
                <input type="checkbox" name="am${item?counter}" id="" class=" " value="E"/>
                <label class=" " style="cursor: pointer;">E.${item.type5!}</label><br/>
            </div>
        </#list>
    </div>
</#if>
<#if fills?? && (fills?size > 0) >
    <!----------------------------------------------- 填空题 -->
    <h3>填空题</h3>
    <#list fills as item>
        <div class="title">
            <span class="">${item?counter}.</span>
        ${item.title!}<br/>
        </div>
        <div class=" " style="">
            <label class=" " style="cursor: pointer;">你的回答：</label>
            <input type="text" name="af${item?counter}" id="" class=" "/>
        </div>
    </#list>
</div>
</#if>
<#if judges?? && (judges?size > 0) >
    <!----------------------------------------------- 判断题 -->
    <h3>判断题</h3>
    <#list judges as item>
        <div class="title">
            <span class="">${item?counter}.</span>
        ${item.title!}<br/>
        </div>
        <div class=" " style="">
            <input type="radio" name="aj${item?counter}" id="" class=" " value="正确"/>
            <label class=" " style="cursor: pointer;">A.${item.type1!}</label><br/>
            <input type="radio" name="aj${item?counter}" id="" class=" " value="错误"/>
            <label class=" " style="cursor: pointer;">B.${item.type2!}</label><br/>

        </div>
    </#list>

</#if>
<#if calculates?? && (calculates?size > 0) >
    <!----------------------------------------------- 计算题 -->
    <h3>计算题</h3>
    <#list calculates as item>
        <div class="title">
            <span class="">${item?counter}.</span>
        ${item.title!}<br/>
        </div>
        <div class=" " style="">
            <label class=" " style="cursor: pointer;">你的回答：</label>
            <input type="text" name="ac${item?counter}" id="" class=" "/>
        </div>
    </#list>

</#if>
<#if subjects?? && (subjects?size > 0) >
<!----------------------------------------------- 主观题 -->
<h3>主观题</h3>
    <#list subjects as item>
    <div class="title">
        <span class="">${item?counter}.</span>
    ${item.title!}<br/>
    </div>
    <div class=" " style="">
        <label class=" " style="cursor: pointer;">你的回答：</label>
        <textarea type="text" name="asub${item?counter}" class="" id="" cols="50" rows="5" >
        </textarea>
    </div>
    </#list>

</#if>
</div>

<input type="button" id="up" style="" onclick="getdate()" value="提交">
<form action="" method="post" style="display:none;" id="answer">
    <input type="text" value="${paperid}" name="id">
    <input type="text" value="${teacCourseId}" name="teacCourseId">
    <input type="text" value="${stuId}" name="stuId">
    <input type="text" value="" name="selectionAnswers" id="selectionAnswers"/>
    <input type="text" value="" name="multiSelectionAnswers" id="multiSelectionAnswers"/>
    <input type="text" value="" name="judgeAnswers" id="judgeAnswers"/>
    <input type="text" value="" name="fillAnswers" id="fillAnswers"/>
    <input type="text" value="" name="calculateAnswers"  id="calculateAnswers"/>
    <input type="text" value="" name="subjectAnswer1" id="subjectAnswer1"/>
    <input type="text" value="" name="subjectAnswer2" id="subjectAnswer2"/>
    <input type="text" value="" name="subjectAnswer3" id="subjectAnswer3"/>
    <input type="text" value="" name="subjectAnswer4" id="subjectAnswer4"/>
    <input type="text" value="" name="subjectAnswer5" id="subjectAnswer5"/>

</form>
</div>
</body>

<script>
    $(function () {
        $("#up").click(function () {
            var time = $("#testtime").text();
            var usedTime = $("#time").text();
            var strs= new Array(); //定义一数组
            strs=usedTime.split(":"); //字符分割
            time = Number(time) - Number(strs[0]);
            $("#answer").attr("action","${rc.contextPath}/paperController/finishTest/"+time);
            $("#answer").submit();
        })

    })
    var sanswers = "";
    var flag = false;
    var b1= $(':radio[name^="as"]');
    var b2= $(':checkbox[name^="am"]');
    var b3= $(':input[name^="af"]');
    var b4= $(':radio[name^="aj"]');
    var b5= $(':input[name^="ac"]');
    var b6= $(':input[name^="asub"]');

    function getdate(){
        getanswer(b1,4);
        console.log("单选题");
        console.log(sanswers);
        $("#selectionAnswers").val(sanswers);
        getanswer(b2,5);
        console.log("多选题");
        console.log(sanswers);
        $("#multiSelectionAnswers").val(sanswers);
        getinputput(b3);
        console.log("填空题");
        console.log(sanswers);
        $("#fillAnswers").val(sanswers);
        getanswer(b4,2);
        console.log("判断题");
        console.log(sanswers);
        $("#judgeAnswers").val(sanswers);
        getinputput(b5);
        console.log("计算题");
        console.log(sanswers);
        $("#calculateAnswers").val(sanswers);
        getsub(b6);

    }
    function getsub(obj) {
        sanswers = "";
        console.log("主观题个数"+obj.length)
        for (var i = 0; i < obj.length; i++) {
            var ans = "";
            ans = obj[i].value.trim();
            if(ans != ""){
                flag = true;
                sanswers = ans;
                console.log("主观题");
                console.log(sanswers);
                $("#subjectAnswer"+(1+i)).val(sanswers);
            }else {
                flag = false;
            }

        }

    }
    function getinputput(obj) {
        var ans = "";
        sanswers = "";
        for (var i = 0; i < obj.length; i++) {
            ans = obj[i].value.trim();
            if(ans != ""){
                flag = true;
                sanswers += ans + ",";
            }else {
                flag = false;
                sanswers = sanswers+ "#" +",";
            }
        }
        sanswers = sanswers.substring(0,sanswers.length-1);
    }

    function getanswer(obj,count){
        sanswers = "";
        var result = "";
        for (var i = 0; i < obj.length/count; i++) {
            result = "";
            flag = false;
            for(var j = 0 ; j < count ; j++){
                if (obj[i*count+j].checked == true){//如果选中，下面的alert就会弹出选中的值
                    result += obj[i*count+j].value;
                    flag = true;
                }
            }
            if(!flag){
                sanswers = sanswers+ "#" +",";
            }else {
                sanswers += result+",";
            }
        }
        //去除最后一个逗号
        sanswers = sanswers.substring(0,sanswers.length-1);
    }

//计时器
    var hh = ${testTime?default(120)};
    var ss = 0;
    var times = setInterval(update,1000);


    function update(){
        isEnd();
        if(ss==0){
            hh -= 1;
            ss = 59;
        }
        ss-=1;
        $("#time").html(hh+":"+ss);
    }

    function isEnd(){
        if(hh==0 && ss==0){
            ss = "00";
            clearInterval(times);
            $("#time").html(hh+":"+ss);
            alert("考试时间到！");
            window.location= "${rc.contextPath}/paperController/finishTest/"+$("#testtime").text();
        }
    }
</script>
</html>
