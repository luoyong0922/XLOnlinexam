<html>
<head>
    <title>学生成绩合格率统计</title>
</head>
<body style="height: 100%; margin: 0">
<div id="container" style="height: 100%"></div>

<HR style="border:1 dashed #987cb9" width="80%" color=#987cb9 SIZE=1>

<div id="container2" style="height: 100%"></div>

<script type="text/javascript" src="${rc.contextPath}/static/js/echarts/echarts.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/static/js/echarts/echarts-gl.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/static/js/echarts/ecStat.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/static/js/echarts/dataTool.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/static/js/echarts/bmap.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/static/js/echarts/simplex.js"></script>

<script type="text/javascript">
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    option = null;
    option = {
        title: {
            text: '总体成绩分析',
            subtext: '实时数据',
            left: 'center'
//            left: 'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            // orient: 'vertical',
            // top: 'middle',
            bottom: 10,
            left: 'center',
            data: [
            <#if map??>
                <#assign x=0 />
                <#list map?keys as key>
                    '${key}',
                    <#assign x=x+1 />
                    <#if x == 3> <#break> </#if>
                </#list>
            </#if>
            ]
        },
        series : [
            {
                type: 'pie',
                radius : '65%',
                center: ['50%', '50%'],
                selectedMode: 'single',
                data:[
                <#if map??>
                    <#assign x=0 />
                    <#list map?keys as key>
                        {value:${map["${key}"]}, name: '${key}'},
                        <#assign x=x+1 />
                        <#if x == 3> <#break> </#if>
                    </#list>
                </#if>
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }

</script>

<script type="text/javascript">

    var dom = document.getElementById("container2");
    var myChart = echarts.init(dom);
    option = null;


    option = {
        title: {
            text: '及格成绩分析',
            subtext: '实时数据',
            left: 'center'
//            left: 'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
// orient: 'vertical',
// top: 'middle',
            bottom: 10,
            left: 'center',
            data: [
            <#if map??>
                <#assign x=0 />
                <#list map?keys as key>

                    <#if (x > 2)>  '${key}',
                         </#if>
                    <#assign x=x+1 />
                </#list>
            </#if>
            ]
        },
        series : [
            {
                type: 'pie',
                radius : '65%',
                center: ['50%', '50%'],
                selectedMode: 'single',
                data:[
                <#if map??>
                <#assign y=0 />
                <#list map?keys as key>
                <#if (y >= 3)>
                {value:${map["${key}"]}, name: '${key}'},
                </#if>
                <#assign y=y+1 />
                </#list>
                </#if>
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>
</body>
