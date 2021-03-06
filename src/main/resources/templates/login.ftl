<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Login</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
        <link href="${rc.contextPath}/static/css/style.css" rel="stylesheet">
	    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/static/css/radio.css">
	</head>

	<body>
		<div class="container" style="margin-top:70px;">
			<div class="row clearfix">
				<div class="col-md-1 column">
				</div>
				<div class="col-md-6 column" style="margin-bottom:20px;">
                    <div class="calendar">
                        <div class="header">
                            <a data-action="prev-month" href="javascript:void(0)" title="Previous Month"><i></i></a>
                            <div class="text" data-render="month-year"></div>
                            <a data-action="next-month" href="javascript:void(0)" title="Next Month"><i></i></a>
                        </div>
                        <div class="months" data-flow="left">
                            <div class="month month-a">
                                <div class="render render-a"></div>
                            </div>
                            <div class="month month-b">
                                <div class="render render-b"></div>
                            </div>
                        </div>
                    </div>
                    <script  src="${rc.contextPath}/static/js/index-data.js"></script>
                    <div style="text-align:center;clear:both;margin-top:50px;"></div>
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
						<div class="checkbox form-group">
                            <div class="toggle_switch">
                                <input type="checkbox" class="switch_3"name="remember-me" id="remember-me">
                                <svg class="checkbox" xmlns="http://www.w3.org/2000/svg" style="isolation:isolate" viewBox="0 0 168 80" >
                                   <path class="outer-ring" d="M41.534 9h88.932c17.51 0 31.724 13.658 31.724 30.482 0 16.823-14.215 30.48-31.724 30.48H41.534c-17.51 0-31.724-13.657-31.724-30.48C9.81 22.658 24.025 9 41.534 9z" fill="none" stroke="#233043" stroke-width="3" stroke-linecap="square" stroke-miterlimit="3"/>
                                   <path class="is_checked" d="M17 39.482c0-12.694 10.306-23 23-23s23 10.306 23 23-10.306 23-23 23-23-10.306-23-23z"/>
                                    <path class="is_unchecked" d="M132.77 22.348c7.705 10.695 5.286 25.617-5.417 33.327-2.567 1.85-5.38 3.116-8.288 3.812 7.977 5.03 18.54 5.024 26.668-.83 10.695-7.706 13.122-22.634 5.418-33.33-5.855-8.127-15.88-11.474-25.04-9.23 2.538 1.582 4.806 3.676 6.66 6.25z"/>
                                </svg>
                                <label style="margin: 13px 0px 10px 80px;font-size:21px;">记住密码</label>
                                <label style="float:right;margin-top:13px;font-size:21px;"><a href="${rc.contextPath}/loginController/toFindPassword">忘记密码？</a></label><br/>
                            </div>
                        </div>
                        <div class="form-group"><input type="button" value="登  录" class="btn btn-success center-block" style="width:70%;font-size:20px;" onclick="checkForm();"/></div>
					</form>
					<div class="col-md-1 column">
					</div>
				</div>
			</div>
	</body>
	<script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
	<script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
	<script type=text/javascript>
		var i = 0;

		function changeImg(img) {
			img.src = "${rc.contextPath}/ImageValidController/getImage?id=" + i;
			i++;
		}
		
			var message = '${msg!}';
			
			if(message != "") {
				alert(message);
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