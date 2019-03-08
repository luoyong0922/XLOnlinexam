<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理员首页</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${rc.contextPath}/static/css/font.css">
    <link rel="stylesheet" href="${rc.contextPath}/static/css/xadmin.css">
    <script type="text/javascript" src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${rc.contextPath}/static/js/xadmin.js"></script>

</head>
<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo"><a href="${rc.contextPath}/toIndex">XLOnlinexam</a></div>
    <div class="left_open">
        <i title="展开左侧栏" class="iconfont">&#xe699;</i>
    </div>
    <ul class="layui-nav left fast-add" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">+新增</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a onclick="x_admin_show('老师','${rc.contextPath}/adminController/addteacher')"><i class="iconfont">&#xe6a2;</i>添加老师</a></dd>
                <dd><a onclick="x_admin_show('学生','${rc.contextPath}/adminController/addstudent')"><i class="iconfont">&#xe6a2;</i>添加学生</a></dd>
                <dd><a onclick="x_admin_show('课程','${rc.contextPath}/courseController/toAddcourse')"><i class="iconfont">&#xe6a8;</i>添加课程</a></dd>
            </dl>
        </li>
    </ul>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;"><#if Session["name"]?exists>${Session["name"]}</#if></a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a onclick="x_admin_show('个人信息','${rc.contextPath}/adminController/showAdminMsg/${Session["num"]!}')">个人信息</a></dd>
                <dd><a onclick="x_admin_show('修改密码','${rc.contextPath}/adminController/toModifyAdminMsg/${Session["num"]!}')">修改密码</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item to-index"><a href="${rc.contextPath}/loginController/logout">注销</a></li>
    </ul>

</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe70b;</i>
                    <cite>学生管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${rc.contextPath}/adminController/toApprovalStudents">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>待审核学生列表</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="${rc.contextPath}/adminController/getAllStudentsByPage">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>查看学生列表</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="${rc.contextPath}/adminController/addstudent">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加学生</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>老师管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${rc.contextPath}/adminController/toApprovalTeachers">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>待审核老师列表</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="${rc.contextPath}/adminController/getAllTeachersByPage">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>查看老师列表</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="${rc.contextPath}/adminController/addteacher">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加老师</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6f5;</i>
                    <cite>课程管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${rc.contextPath}/courseController/getCourseMessage?role=3">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>查看课程列表</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="${rc.contextPath}/courseController/toAddcourse">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加课程</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>试卷管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <#--<#list courses as c>-->
                        <li>
                            <a _href="${rc.contextPath}/adminController/adminSeeAllPapersBypage">
                                <i class="iconfont">&#xe6a7;</i>
                                <cite>查看试卷列表</cite>
                            </a>
                        </li >
                    <#--</#list>-->
                </ul>
            </li>

            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6cb;</i>
                    <cite>查看题库</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                <#-- // 1：多选题，2：单选题，3:判断题，4：填空题，5：计算题，6：主观题-->
                    <li>
                        <a _href="${rc.contextPath}/paperController/getMyQuestions?questionType=1">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>多选题 </cite>
                        </a>
                    </li >
                    <li>
                        <a _href="${rc.contextPath}/paperController/getMyQuestions?questionType=2">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>单选题 </cite>
                        </a>
                    </li >
                    <li>
                        <a _href="${rc.contextPath}/paperController/getMyQuestions?questionType=3">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>判断题 </cite>
                        </a>
                    </li >
                    <li>
                        <a _href="${rc.contextPath}/paperController/getMyQuestions?questionType=4">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>填空题 </cite>
                        </a>
                    </li >
                    <li>
                        <a _href="${rc.contextPath}/paperController/getMyQuestions?questionType=5">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>计算题 </cite>
                        </a>
                    </li >
                    <li>
                        <a _href="${rc.contextPath}/paperController/getMyQuestions?questionType=6">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>主观题 </cite>
                        </a>
                    </li >
                </ul>
            </li>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='${rc.contextPath}/adminController/toWelcome' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <div style="text-align:center;">Copyright ©2018 XLOnlinexam v1.0 All Rights Reserved</div>
</div>
<!-- 底部结束 -->

</body>
</html>