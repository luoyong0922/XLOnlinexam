<html>
<head>
    <title>管理员主页</title>
</head>
<body>
<h1>欢迎
    <b style="color: deepskyblue"><#if Session["name"]?exists>${Session["name"]}</#if></b>
    登录<em>XL在线考试管理系统</em></h1>

<a href="${rc.contextPath}/loginController/logout">注销</a><br>
<a href="${rc.contextPath}/courseController/toAddcourse">添加课程</a><br>
<a href="${rc.contextPath}/courseController/getCourseMessage?role=3">课程管理</a><br>
<a href="${rc.contextPath}/adminController/showAdminMsg/${Session["num"]!}">个人中心</a><br>
<a href="${rc.contextPath}/adminController/toModifyAdminMsg/${Session["num"]!}">修改密码</a><br>
<a href="${rc.contextPath}/paperController/getMyQuestions">我的题库</a><br>
<a href="${rc.contextPath}/adminController/getAllTeachersByPage">所有老师</a><br>
<a href="${rc.contextPath}/adminController/getAllStudentsByPage">所有学生</a><br>
<a href="${rc.contextPath}/adminController/adminSeeAllPapersBypage">所有试卷</a><br>
<br/>
<br/>
<br/>

<body>
</html>