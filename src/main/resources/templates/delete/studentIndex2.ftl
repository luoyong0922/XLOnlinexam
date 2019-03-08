<html>
<head>
    <title>学生主页</title>
</head>
<body>
<h1>欢迎
    <b style="color: deepskyblue"><#if Session["name"]?exists>${Session["name"]}</#if></b>
    登录<em>XL在线考试管理系统</em></h1>

<a href="${rc.contextPath}/loginController/logout">注销</a><br>
<a href="${rc.contextPath}/studentController/showStudentMessage/${Session["num"]!}">个人中心</a><br>
<a href="${rc.contextPath}/studentController/toModifyStudentMsg/${Session["num"]!}">修改密码</a><br>

<a href="${rc.contextPath}/studentController/toSelectCourse/">我要选课</a><br>

<#--<a href="${rc.contextPath}/teacherController/showTeacherMessage/${Session["num"]!}">个人中心</a><br>-->
<#--<a href="${rc.contextPath}/teacherController/toModifyMessage/${Session["num"]!}">修改密码</a><br>-->
<a href="${rc.contextPath}/courseController/getCourseMessage?role=1">我的课程</a><br>
<br/>
<br/>
<br/>

<body>
</html>