<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>时钟</title>
    <style type="text/css">
        ul,li{
            list-style: none;
            margin: 0;
            padding: 0;
        }
        ul{
            position: absolute;
            left: 260px;
            top: 50%;
            margin-top: -18px;
        }
        ul:nth-of-type(2){
            left: 426px;

        }
        li{
            width: 16px;
            height: 16px;
            border-radius: 50%;
            margin-bottom: 6px;
            background: url(${rc.contextPath}/static/images/favicon.ico);
        }
        .box {
            background: url(${rc.contextPath}/static/images/003.jpg);
            width: 1024px;
            height: 701px;
            margin: 0 auto;
            position: relative;
        }
        .clock {
            width: 625px;
            height: 116px;
            background: url(${rc.contextPath}/static/images/1.png);;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: -58px;
            margin-left: -312px;
        }
        .clockIco {
            margin-top: 26px;
            margin-left: 26px;
            float: left;
        }
        .numClock {
            margin-left: 30px;
            margin-top: 18px;
            overflow: hidden;
            float: right;
        }
        .numClock div {
            margin-right: 40px;
            float: left;
        }
        .numClock span {
            width: 60px;
            height: 76px;
            display: inline-block;
            background: black;
            border: 1px solid white;
            font: bolder 40px/76px 黑体;
            color: white;
            text-align: center;
        }
        .dong {
            animation: run 700ms linear;

        }
        @-webkit-keyframes run {
            from {
                transform: rotateX(0deg);
            }
            to {
                transform: rotateX(360deg);
            }
        }
        .hidden{
            display: none;
        }
        .
    </style>
</head>

<body>
<div class="box">
    <div class="clock">
        <img class="clockIco" style="margin-top:39px;" src="${rc.contextPath}/static/images/004.jpg" />
        <div class="numClock">
            <div class="hour">
                <span>1</span>
                <span>1</span>
            </div>
            <ul>
                <li></li>
                <li></li>
            </ul>
            <div class="minute">
                <span>1</span>
                <span>1</span>
            </div>
            <ul>
                <li></li>
                <li></li>
            </ul>
            <div class="second">
                <span>1</span>
                <span class="miao">1</span>
            </div>
        </div>

    </div>
</div>

<script type="text/javascript">
    var ulS = document.querySelectorAll('ul');
    var hourS = document.querySelector(".numClock").querySelectorAll("span")[0];
    var hourG = document.querySelector(".numClock").querySelectorAll("span")[1];
    var minuteS = document.querySelector(".numClock").querySelectorAll("span")[2];
    var minuteG = document.querySelector(".numClock").querySelectorAll("span")[3];
    var secondS = document.querySelector(".numClock").querySelectorAll("span")[4];
    var secondG = document.querySelector(".numClock").querySelectorAll("span")[5];
    clock();
    //开启定时器
    setInterval(clock, 1000);

    function clock() {
        var date = new Date();
        //////////////////////////获取小时//////////////////////////////////
        var hour = date.getHours();

        //获取小时的十位数
        //hour/10
        hourS.innerHTML = parseInt(hour / 10);
        //获取小时的个位数
        //hour%10
        hourG.innerHTML = parseInt(hour % 10);

        //////////////////////////获取分钟//////////////////////////////////
        var minute = date.getMinutes();
        //console.log(minute);
        //获取分钟的十位数
        //minute/10
        minuteS.innerHTML = parseInt(minute / 10);
        //获取分钟的个位数
        //minute%10
        minuteG.innerHTML = parseInt(minute % 10);

        //////////////////////////获取秒//////////////////////////////////
        var second = date.getSeconds();
        console.log(second);
        //获取秒的十位数
        //second/10
        secondS.innerHTML = parseInt(second / 10);
        //获取秒的个位数
        //second%10
        secondG.innerHTML = parseInt(second % 10);

    }

    var onOff = true;
    setInterval(function() {
        if(onOff) {
            secondG.className = "dong";
            ulS[0].className = 'hidden'
            ulS[1].className = 'hidden'
            onOff = false;
        } else {
            secondG.className = "";
            ulS[0].className = ''
            ulS[1].className = ''
            onOff = true;
        }
    }, 1000)
</script>
</body>

</html>

