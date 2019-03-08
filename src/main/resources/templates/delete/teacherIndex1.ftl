<html>
<head>
    <title>老师主页</title>
</head>
<body>
<h1>欢迎
    <b style="color: deepskyblue"><#if Session["name"]?exists>${Session["name"]}</#if></b>
    登录<em>XL在线考试管理系统</em></h1>

<a href="${rc.contextPath}/loginController/logout">注销</a><br>
<a href="${rc.contextPath}/paperController/toPublicPaper">发布考试</a><br>
<a href="${rc.contextPath}/teacherController/showTeacherMessage/${Session["num"]!}">个人中心</a><br>
<a href="${rc.contextPath}/teacherController/toModifyMessage/${Session["num"]!}">修改密码</a><br>
<a href="${rc.contextPath}/teacherController/getAllstuScore">试卷管理</a><br>
<a href="${rc.contextPath}/courseController/getCourseMessage?role=2">我的课程</a><br>
<a href="${rc.contextPath}/paperController/getMyQuestions?id=${Session["id"]!}">我的题库</a><br>
<br/>
<br/>
<br/>

<body>
</html>