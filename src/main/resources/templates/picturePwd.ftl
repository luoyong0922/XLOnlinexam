<!doctype html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="自动九宫格锁屏js">
    <meta name="keywords" content="九宫格,锁屏,html,js">
    <meta http-equiv="Cache-Control" content="max-age=7200" />
    <title>九宫格锁屏-登录页面</title>
</head>

<body>
<form action="${rc.contextPath}/loginController/doPicPwdLogin" method="post" style="display: none" id="form">
    <input type="text" value="${account}" name="account"/>
    <input type="text" value="${role}" name="role"/>
    <input type="text" id="pwd" name="pwd"/>
</form>
<style>

</style>
<!--
<div style="font-size: 32px;color: #fff;width:240px;height:240px;margin-top: 132px;margin-left: 42%;">
<table cellspacing="46" style="line-height: 40px">
    <tr>
        <td>1</td>
        <td>2</td>
        <td>3</td>
    </tr>
    <tr>
        <td>4</td>
        <td>5</td>
        <td>6</td>
    </tr>
    <tr>
        <td>7</td>
        <td>8</td>
        <td>9</td>
    </tr>
</table>

</div>
-->
<script src="${rc.contextPath}/static/js/fcode/jquery.min.js"></script>
<!--这是是锁屏要引入的js,需要放在jquery的后面-->
<script src="${rc.contextPath}/static/js/fcode/fcode.min.js"></script>
<script type="text/javascript">
    fcode.bgColor = '#000'; //背景颜色

    fcode.fontColor = '#FFF'; //圆环颜色
    
    fcode.lineColor = "#FFF"; //连线的颜色

    fcode.lineErrorColor = "#00a254"; //连线错误颜色

    fcode.lineSuceessColor = "#cc1c21"; //连线正确颜色

    fcode.Debug = true; //用来设置是否禁止F12，鼠标右键

    fcode.bgImage = '${rc.contextPath}/static/images/background.jpg'; //设置背景图片，优先于背景颜色

    //定义九宫格解锁上方的html内容
    fcode.customHtml = '<div style="margin-top:130px;"><br /><br /></div>';

    //登录回调
    fcode.Login(function(pwd) {
       $("#pwd").val(pwd);
      $("#form").submit();
      /*  if(pwd == 1235789) {
            fcode.succes(); //连线显示正确
        } else {
            fcode.error(); //连线显示错误
        }*/
    });
</script>
</body>

</html>