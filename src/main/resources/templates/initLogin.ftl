<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">

  <title>正在登录</title>

  <link rel="stylesheet" href="${rc.contextPath}/static/css/normalize.css">

    <link rel="stylesheet" href="${rc.contextPath}/static/css/initStyle.css" media="screen" type="text/css" />

</head>

<body>

  <div class="code-editor"><span class="control" onclick="redirect();"></span><span class="control" onclick="redirect();"></span><span class="control" onclick="redirect();"></span>
        <pre class="numbers linenums prettyprint prettyprinted">
            <ol class="linenums" style=" line-height: 5px;">
                <li class="L0">
                    <code class="css">
						<span class="pln">$</span>
						<span class="pun">(</span>
						<span class="kwd">function</span>
						<span class="pun">(){</span>
					</code>
				</li>
				<li class="L1">
					<code class="css">
						<span class="pln" style="margin-left: 20px;"></span>
						<span class="kwd">var</span>
						<span class="pln"> date </span>
						<span class="pun">=</span>
						<span class="pln"></span>
						<span class="str">'欢迎进入课堂测评系统，'</span>
						<span class="pun">;</span>
					</code>
				</li>
				<li class="L2">
				    <code class="css">
						<span class="pln" style="margin-left: 20px;"></span>
						<span class="pln"> date </span>
						<span class="pun">+=</span>
						<span class="pln"></span>
						<span class="str">'本系统致力于数字化校园的建设。'</span>
						<span class="pun">;</span>
                    </code>
                </li>
                <li class="L3">
                    <code class="css">
						<span class="pln" style="margin-left: 20px;"></span>
						<span class="pln"> date </span>
						<span class="pun">+=</span>
						<span class="pln"></span>
						<span class="str">'如果你对软件开发感兴趣的话，'</span>
						<span class="pun">;</span>
                    </code>
                </li>
                <li class="L4">
                    <code class="css">
						<span class="pln" style="margin-left: 20px;"></span>
						<span class="pln"> date </span>
						<span class="pun">+=</span>
						<span class="pln"></span>
						<span class="str">'我们诚挚欢迎你的加入！'</span>
						<span class="pun">;</span>
                    </code>
                </li>
                <li class="L5">
                    <code class="css">
						<span class="pln" style="margin-left: 20px;"></span>
						<span class="kwd">console.log</span>
						<span class="pun">(</span>
						<span class="pln">date</span>
						<span class="pun">)</span>
						<span class="pun">;</span>
                    </code>
                </li>
                <li class="L6">
                    <code class="css">
						<span class="pln" style="margin-left: 20px;"></span>
						<span class="kwd">alert</span>
						<span class="pun">(</span>
						<span class="str">'打开开发者工具，有彩蛋在等你哦！'</span>
						<span class="pun">)</span>
						<span class="pun">;</span>
                    </code>
                </li>
                <li class="L1">
                    <code class="css">
						<span class="pln"> </span>
						<span class="pun">})</span>
                    </code>
                </li>
            </ol>
        </pre>
      </div>

    <script src='${rc.contextPath}/static/js/jquery.js'></script>
    <script src='${rc.contextPath}/static/js/prettify.js'></script>
    <script src='${rc.contextPath}/static/js/timer.js'></script>
    <div style="text-align:center;clear:both"></div>
</body>
<script>
$('body').oneTime('21s','A',function(){
alert('打开开发者工具，有彩蛋在等你哦！')

 var d1 = '           .##......##.########.##........######...#######..##.....##.########';
 var d2 = '           .##..##..##.##.......##.......##....##.##.....##.###...###.##......';
 var d3 = '           .##..##..##.##.......##.......##.......##.....##.####.####.##......';
 var d4 = '           .##..##..##.######...##.......##.......##.....##.##.###.##.######..';
 var d5 = '           .##..##..##.##.......##.......##.......##.....##.##.....##.##......';
 var d6 = '           .##..##..##.##.......##.......##....##.##.....##.##.....##.##......';
 var d7 = '           ..###..###..########.########..######...#######..##.....##.########';
 console.log(d1);
 console.log(d2);
 console.log(d3);
 console.log(d4);
 console.log(d5);
 console.log(d6);
 console.log(d7);
 console.log('');
 console.log('欢迎进入课堂测评系统，本系统致力于数字化校园的建设。如果你对软件开发感兴趣的话，我们诚挚欢迎你的加入！');
 console.log('QQ群：229872283');
 });
 
$('body').oneTime('25s','B',function(){
	redirect();
 });
 function redirect(){
 	window.location.href = '${rc.contextPath}/loginController/redirectPage';
 }
</script>
</html>