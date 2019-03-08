<html>
<head>
    <title>学生成绩合格率统计</title>
</head>
<body style="height: 100%; margin: 0">
<div id="container" style="height: 100%"></div>

<HR style="border:1 dashed #987cb9" width="80%" color=#987cb9 SIZE=1>

<script type="text/javascript" src="${rc.contextPath}/static/js/echarts/echarts.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/static/js/echarts/echarts-gl.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/static/js/echarts/ecStat.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/static/js/echarts/dataTool.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/static/js/echarts/bmap.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/static/js/echarts/simplex.js"></script>

<script type="text/javascript">
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    app.title = '成绩统计图';
    option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:[
            <#if map??>
                <#list map?keys as key>
                    <#if map["${key}"] != 0>
                        {value:${map["${key}"]}, name: '${key}'},
                    </#if>
                </#list>
            </#if>
                ]
        },
        series: [
            {
                name:'XL',
                type:'pie',
                selectedMode: 'single',
                radius: [0, '30%'],

                label: {
                    normal: {
                        position: 'inner'
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                <#if map??>
                    <#assign x=0 />
                    <#list map?keys as key>
                        <#if map["${key}"] != 0>
                        {value:${map["${key}"]}, name: '${key}'},
                        </#if>
                        <#assign x=x+1 />
                        <#if x == 3> <#break> </#if>
                    </#list>
                </#if>
                ]
            },
            {
                name:'XL',
                type:'pie',
                radius: ['40%', '55%'],
                label: {
                    normal: {
                        formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                        backgroundColor: '#eee',
                        borderColor: '#aaa',
                        borderWidth: 1,
                        borderRadius: 4,
                        // shadowBlur:3,
                        // shadowOffsetX: 2,
                        // shadowOffsetY: 2,
                        // shadowColor: '#999',
                        // padding: [0, 7],
                        rich: {
                            a: {
                                color: '#999',
                                lineHeight: 22,
                                align: 'center'
                            },
                            // abg: {
                            //     backgroundColor: '#333',
                            //     width: '100%',
                            //     align: 'right',
                            //     height: 22,
                            //     borderRadius: [4, 4, 0, 0]
                            // },
                            hr: {
                                borderColor: '#aaa',
                                width: '100%',
                                borderWidth: 0.5,
                                height: 0
                            },
                            b: {
                                fontSize: 16,
                                lineHeight: 33
                            },
                            per: {
                                color: '#eee',
                                backgroundColor: '#334455',
                                padding: [2, 4],
                                borderRadius: 2
                            }
                        }
                    }
                },
                data:[
                <#if map??>
                    <#assign y=0 />
                    <#list map?keys as key>
                        <#if map["${key}"] != 0>
                        <#if (y >= 3)>
                            {value:${map["${key}"]}, name: '${key}'},
                        </#if>
                        </#if>
                        <#assign y=y+1 />
                    </#list>
                </#if>
                ]
            }
        ]
    };

    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>
</body>
