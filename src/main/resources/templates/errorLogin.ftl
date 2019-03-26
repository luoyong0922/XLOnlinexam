<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<title>Login</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

	</head>

	<body>
		<div class="container" style="margin-top:80px;">
			<div class="row clearfix">
				<div class="col-md-1 column">
				</div>
				<div class="col-md-6 column" style="margin-bottom:20px;">
					<div class="carousel slide" id="carousel-782648">
						<ol class="carousel-indicators">
							<li class="active" data-slide-to="0" data-target="#carousel-782648">
							</li>
							<li data-slide-to="1" data-target="#carousel-782648">
							</li>
							<li data-slide-to="2" data-target="#carousel-782648">
							</li>
						</ol>
						<div class="carousel-inner">
							<div class="item active">
								<img alt="" src="${rc.contextPath}/static/images/1.jpg" />
								<div class="carousel-caption">
									<h4>
                                  First Thumbnail label
                              </h4>
									<p>
										Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
									</p>
								</div>
							</div>
							<div class="item">
								<img alt="" src="${rc.contextPath}/static/images/2.jpg" />
								<div class="carousel-caption">
									<h4>
                                  Second Thumbnail label
                              </h4>
									<p>
										Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
									</p>
								</div>
							</div>
							<div class="item">
								<img alt="" src="${rc.contextPath}/static/images/3.jpg" />
								<div class="carousel-caption">
									<h4>
                                  Third Thumbnail label
                              </h4>
									<p>
										Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
									</p>
								</div>
							</div>
						</div>
						<a class="left carousel-control" href="#carousel-782648" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
						<a class="right carousel-control" href="#carousel-782648" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
					</div>
				</div>
				<div class="col-md-4 column">
					<form role="form" action="${rc.contextPath}/loginController/dologin" method="post" id="loginForm">
						<div class="form-group">
							<label for="account">账号：</label><input type="text" class="form-control" id="account" name="account" />
						</div>
						<div class="form-group">
							<label for="password">密码：</label><input type="password" class="form-control" id="password" name="password" />

						</div>

						<div class="form-group">
							<label for="validate">验证码：</label>
							<input type="text" class="form-control" id="validate" name="validate" style="max-width:150px;size:10px;display:inline;" />
							<img style="cursor:hand;border:none;}" name="imgcode" id="imgcode" align="absmiddle" src="${rc.contextPath}/ImageValidController/getImage" onclick="changeImg(this)" />
							<!--验证码当图片处理，点击可刷新-->
						</div>
						<div class="checkbox">
							<label style="float:left;"><input  type="checkbox" name="remember-me" id="remember-me" />记住密码</label>
							<label style="float:right;"><a href="${rc.contextPath}/loginController/toFindPassword">忘记密码？</a></label><br/>
						</div>
						<div class="form-group"><input type="button" value="登录" class="btn btn-default center-block" style="width:70%;" onclick="checkForm();"></input>
						</div>
					</form>
					<div class="col-md-1 column">
					</div>
				</div>
			</div>
	</body>
	<script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
	<script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
	<script type=text/javascript>
		alert("账号或密码错误！");
		var i = 0;

		function changeImg(img) {
			img.src = "${rc.contextPath}/ImageValidController/getImage?id=" + i;
			i++;
		}

		//校验 验证码
		function checkForm() {
			var account = $("#account").val();
			var password = $("#password").val();
			var validate = $("#validate").val();
			if("" == (account)){
				alert("账号不能为空！");
				return;
			}
			if("" == (password)){
				alert("密码不能为空！");
				return;
			}
			if("" == (validate)){
				alert("验证码不能为空！");
				return;
			}
			var form = document.getElementById('loginForm');
			$.ajax({
				type: "get",
				url: '${rc.contextPath}/ImageValidController/valid/' + validate,
				success: function(data) {
					if(data.code == "success") {
						form.submit();
					} else {
						alert(data.message);
					}
				}
			});
		}
	</script>

</html>