<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8">
		<title>正在登录</title>
		<!-- <link rel="stylesheet" href="css/font-awesome.min.css"> -->
		<link rel="stylesheet" type="text/css" href="${rc.contextPath}/static/css/bootstrap-grid.min.css" />
		<link href="${rc.contextPath}/static/css/btn.css" type="text/css" rel="stylesheet">
		<link href="${rc.contextPath}/static/css/Location.css" type="text/css" rel="stylesheet">
	</head>

	<script type="text/javascript">
		document.onkeydown = function(event) {
			if((event.ctrlKey) && (event.keyCode == 115 || event.keyCode == 83)) {
				event.returnValue = false;
				return;
			}
		}
	</script>

	<body class="body--ready" data-pinterest-extension-installed="cr1.39.1" oncontextmenu=self.event.returnValue=false onselectstart="return false">

		<canvas class="canvas" width="100%" height="100%"></canvas>
		<div id="willerce">
			<div>
				<img src="${rc.contextPath}/static/images/pkq.png" id="logo" title="皮" />
				<h1>课堂测评系统</h1>
				<a>我们用心在做！</a>
			</div>
			<script type="text/javascript">
				function duihua() {
					alert("公告：课堂测评系统致力于数字化校园的建设,欢迎你的加入！")
				}
			</script>
			<br>
			<div class="menu">
				<!--  Hi ${Session.SPRING_SECURITY_CONTEXT.authentication.principal.username!} ! -->
				<a style=" padding: 0px 20px; " class="btn btn-lg ju" href="javascript:void(0);" onclick="duihua()">公告</a>
			</div>
		</div>

		<!--动画脚本-->
		<script src="${rc.contextPath}/static/js/S.js"></script>
		<script type="text/javascript">
			S.init();
		</script>
		<script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
		<script src="${rc.contextPath}/static/js/jquery.marquee.min.js"></script>

		<script>
			function bgChange() {
				var lis = $('.lib');
				for(var i = 0; i < lis.length; i += 2) {
					lis[i].style.background = 'rgba(246, 246, 246, 0.5)';
				}
				//定时执行，12秒后执行
				var t1 = window.setTimeout(reDirectPage, 1000 * 12);
				//去掉定时器的方法  
			//  window.clearTimeout(t1);
			}
			window.onload = bgChange();

			function reDirectPage() {
				window.location.href = '${rc.contextPath}/loginController/redirectPage';
			}
			
		</script>
	</body>

</html>