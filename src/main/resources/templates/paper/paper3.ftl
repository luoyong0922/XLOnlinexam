<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>批阅试卷</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
</head>

<body style="background-color: #FFFFE9;">
<div class="container">
<div class="content" style="width:80%;margin:auto auto;">

    <div >
        <h1 style="text-align: center;"><strong>${courseName!}</strong>考试</h1>
        <h3 style="float: right;">考试时长:<i>${testTime}</i>分钟</h3>
    </div><br/>

<div id="question_box">

    <!-- Append question's html code here. -->
    <!----------------------------- 单选题 -->
<#if selects?? && (selects?size > 0) >
    <h3>单项选择题（每小题${standard.selVal}分,共${standard.selCount}题）</h3>
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
            <label class=" " style="cursor: pointer;">回答：</label>
            <input type="text" value="" id="ks${item?counter}" style="color:blue;font-size: 16px;border:none;"/>
            <label class=" " style="cursor: pointer;">参考答案：</label>
            <input type="text" value="${item.answer}" style="color:red;font-size: 16px;border:none;"/><br/>
        </div>
    </#list>
    <#list selectAnsList as ans>
        <input type="text" value="${ans}" id="ksa${ans?counter}" style="display: none;"/>
    </#list>
    <label class=" " style="cursor: pointer;">单项选择题得分：</label>
    <input type="text" value="${stuScore.selectionCount}" style="color:red;font-size: 20px;border:none;"/><br/>
</#if>
<#if multiSelects?? && (multiSelects?size > 0) >
    <!----------------------------------------------- 多选题 -->
    <h3>多项选择题（每小题${standard.mutilVal}分,共${standard.mutilCount}题）</h3>
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
                <label class=" " style="cursor: pointer;">回答：</label>
                <input type="text" value="" id="kms${item?counter}" style="color:blue;font-size: 16px;border:none;"/>
                <label class=" " style="cursor: pointer;">参考答案：</label>
                <input type="text" value="${item.answer}" style="color:red;font-size: 16px;border:none;"/><br/>
            </div>
        </#list>
        <#list mutilAnsList as ans>
            <input type="text" value="${ans}" id="kmsa${ans?counter}" style="display: none;"/>
        </#list>
        <label class=" " style="cursor: pointer;">多项选择题得分：</label>
        <input type="text" value="${stuScore.multiSelectionCount}" style="color:red;font-size: 20px;border:none;"/><br/>
    </div>
</#if>
<#if fills?? && (fills?size > 0) >
    <!----------------------------------------------- 填空题 -->
    <h3>填空题（每小题${standard.fillVal}分,共${standard.fillCount}题）</h3>
    <#list fills as item>
        <div class="title">
            <span class="">${item?counter}.</span>
        ${item.title!}<br/>
        </div>
        <div class=" " style="">
            <label class=" " style="cursor: pointer;">回答：</label>
            <input type="text" name="af${item?counter}" id="af${item?counter}" style="color:blue;font-size: 16px;border:none;"/>
            <label class=" " style="cursor: pointer;">参考答案：</label>
            <input type="text" value="${item.answer1}" style="color:red;font-size: 16px;border:none;"/><br/>
        </div>
    </#list>
    <#list fillAnsList as ans>
        <input type="text" value="${ans}" id="kfa${ans?counter}" style="display: none;"/>
    </#list>
    <label class=" " style="cursor: pointer;">填空题得分：</label>
    <input type="text" value="${stuScore.fillCount}" style="color:red;font-size: 20px;border:none;"/><br/>
</div>
</#if>
<#if judges?? && (judges?size > 0) >
    <!----------------------------------------------- 判断题 -->
    <h3>判断题（每小题${standard.judgeVal}分,共${standard.judgeCount}题）</h3>
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
            <label class=" " style="cursor: pointer;">回答：</label>
            <input type="text" value="" id="kj${item?counter}" style="color:blue;font-size: 16px;border:none;"/>
            <label class=" " style="cursor: pointer;">参考答案：</label>
            <input type="text" value="${item.answer}" style="color:red;font-size: 16px;border:none;"/><br/>

        </div>
    </#list>
    <#list judgeAnsList as ans>
        <input type="text" value="${ans}" id="kja${ans?counter}" style="display: none;"/>
    </#list>
    <label class=" " style="cursor: pointer;">判断题得分：</label>
    <input type="text" value="${stuScore.judgeCount}" style="color:red;font-size: 20px;border:none;"/><br/>

</#if>
<#if calculates?? && (calculates?size > 0) >
    <!----------------------------------------------- 计算题 -->
    <h3>计算题（每小题${standard.calculateVal}分,共${standard.calculateCount}题）</h3>
    <#list calculates as item>
        <div class="title">
            <span class="">${item?counter}.</span>
        ${item.title!}<br/>
        </div>
        <div class=" " style="">
            <label class=" " style="cursor: pointer;">回答：</label>
            <input type="text" name="ac${item?counter}" id="ac${item?counter}" style="color:blue;font-size: 16px;border:none;"/>
            <label class=" " style="cursor: pointer;">参考答案：</label>
            <input type="text" value="${item.answer1}" style="color:red;font-size: 16px;border:none;"/><br/>
        </div>
    </#list>
    <#list calculateAnsList as ans>
        <input type="text" value="${ans}" id="kca${ans?counter}" style="display: none;"/>
    </#list>
    <label class=" " style="cursor: pointer;">计算题得分：</label>
    <input type="text" value="${stuScore.calculateCount}" style="color:red;font-size: 20px;border:none;"/><br/>
</#if>
<#if subjects?? && (subjects?size > 0) >
<!----------------------------------------------- 主观题 -->
<h3>主观题（每小题${standard.subjectVal}分,共${standard.subjectCount}题）</h3>
    <#list subjects as item>
    <div class="title">
        <span class="">${item?counter}.</span>
    ${item.title!}<br/>
    </div>
    <div class=" " style="">
        <label class=" " style="cursor: pointer;">回答：</label>
        <textarea type="text" name="asub${item?counter}" id="asub${item?counter}" cols="50" rows="5" style="color:blue;font-size: 16px;border:none;">
        </textarea>
        <label class=" " style="cursor: pointer;">参考答案：</label>
        <textarea type="text" id="" cols="50" rows="5" style="color:red;font-size: 16px;border:none;">
        ${item.answer!}
        </textarea>
    </div>
    <label class=" " style="cursor: pointer;" >得分：</label>
    <input type="number" id="xyp${item?counter}" value="${stuScore.subjectCount!}" style="color:red;font-size: 20px;border:none;"/><br/>
    </#list>
    <#list subjectAnsList as ans>
    <input type="text" value="${ans}" id="kect${ans?counter}" style="display: none;"/>
    </#list>
</div>
</#if>
</div>
<input type="button" id="up" class="btn btn-info center-block" style="width:30%;" value="提交">
</div>

<form action="${rc.contextPath}/paperController/toMarking" method="post" style="display:none;" id="answer">
    <input type="text" value="${paperid!}" name="id">
</form>
</div>
</body>

<script>
    $(function () {
        $("#up").click(function () {
            var score6 = 0;
            //所有主观题成绩对象
            var xyp = $(':input[id^="xyp"]');
            xyp.each(function () {
                console.log(this.value);
                //计算主观题总成绩
                score6 = Number(this.value)+ Number(score6);
            })
            $("#answer").attr("action","${rc.contextPath}/paperController/doMarking/"+score6);
            $("#answer").submit();
        });
        //所有单选答案和回答对象
        var bs = $(':input[id^="ks"]');
        for(var i=0;i<=bs.length/2;i++){
            if($("#ksa"+i).val() != "#"){
                $("#ks"+i).val($("#ksa"+i).val());
            }else {
                $("#ks"+i).val("未作答");
            }
        }
        //所有多选题答案和回答对象
        var bms = $(':input[id^="kms"]');
        for(var i=0;i<=bms.length/2;i++){
            if($("#kmsa"+i).val() != "#"){
                $("#kms"+i).val($("#kmsa"+i).val());
            }else {
                $("#kms"+i).val("未作答");
            }
        }
        //所有填空题答案和回答对象
        var bf = $(':input[id^="kf"]');
        for(var i=0;i<=bf.length;i++){
            if($("#kfa"+i).val() != "#"){
                $("#af"+i).val($("#kfa"+i).val());
            }else {
                $("#af"+i).val("未作答");
            }
        }
        //所有判断题答案和回答对象
        var bf = $(':input[id^="kj"]');
        for(var i=0;i<=bf.length/2;i++){
            if($("#kja"+i).val() != "#"){
                $("#kj"+i).val($("#kja"+i).val());
            }else {
                $("#kj"+i).val("未作答");
            }
        }
        //所有计算题答案和回答对象
        var b5= $(':input[name^="ac"]');
        console.log(b5.length)
        for(var i=0;i<=b5.length;i++){
            if($("#kca"+i).val() != "#"){
                console.log(1)
                $("#ac"+i).val($("#kca"+i).val());
            }else {
                console.log(2)
                $("#ac"+i).val("未作答");
            }
        }
        //所有主观题答案和回答对象

        for(var i=1;i<=5;i++){
            if($("#kect"+i).val() != "#"){
                $("#asub"+i).val($("#kect"+i).val());
            }else {
                $("#asub"+i).val("未作答");
            }
        }
    });

</script>
</html>
