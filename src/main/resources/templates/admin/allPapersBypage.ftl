<html>
<head>

    <title>管理员分页查看所有试卷</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${rc.contextPath}/static/css/bootstrap.min.css">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/popper.min.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
    <style>
        .xyp{
            display: inline-block;
            float: right;
            margin: 24px;
        }
    </style>
</head>
<body>
<div class="container">

    <h2 style="text-align: center">试卷列表</h2><br>
<#--${pageInfo}-->
    <form action="${rc.contextPath}/adminController/adminSeeAllPapersBypage?pageIndex=1" method="post">
        课程名称:<input type="text" name="courseName" value="${courseName!}"/>
        授课老师：<input type="text" name="teacName" value="${teacName!}"/>
        <input type="submit" class="btn-primary" value="搜索">
    </form>

    <div class="container">
        <table class="table table-hover" id="Test">
            <thead>

            <tr>
                <th>序号</th>
                <th>试卷编号</th>
                <th>课程名称</th>
                <th>任课教师</th>
                <th>考试时间</th>
                <th>学生姓名</th>
                <th>状态</th>
                <th>成绩</th>
            </tr>
            </thead>
            <tbody>
    <#list pageInfo.list?sort_by("papeState") as paper>
        <tr <#if paper.papeState == 0>class="table-warning"<#elseif paper.papeState == 1>class="table-info" </#if> >
            <td>${paper?counter}</td>
            <td>${paper.id!}</td>
            <td>${paper.courseName!}</td>
            <td>${paper.teacName!}</td>
            <td>${paper.testTime?string("yyyy-MM-dd")}</td>
            <td>${paper.stuName!}</td>
            <td><#if paper.papeState == 0>待批阅<#elseif paper.papeState == 1>已批阅</#if> </td>
            <td>${paper.totalScore!}</td>
        </tr>

    </#list>
            </tbody>
        </table>
    </div>
<#--<input type="button" onclick="deletes()" value="批量删除">-->
    <#--<br/>-->
    <#--当前是第${pageInfo.pageNum}页,共${pageInfo.pages}页，总记录数为${pageInfo.total}条-->
    <#--<br/>-->
    <#--<div class="col-md-12">-->
        <#--<nav aria-label="Page navigation">-->
            <#--<ul class="pagination">-->
                <#--<li>-->
                    <#--<a href="${rc.contextPath}/adminController/adminSeeAllPapersBypage?pageIndex=1&keywords=${keywords!}">首页</a>-->
                <#--</li>-->
            <#--&lt;#&ndash;<!-- 如果还有前页就访问当前页码-1的页面， &ndash;&gt;&ndash;&gt;-->
                    <#--<#if pageInfo.hasPreviousPage>-->
                        <#--<li>-->
                            <#--<a href="${rc.contextPath}/adminController/adminSeeAllPapersBypage?pageIndex=${pageInfo.pageNum-1}&keywords=${keywords!}" aria-label="Previous">-->
                                <#--<span aria-hidden="true">上一页</span>-->
                            <#--</a>-->
                        <#--</li>-->
                    <#--</#if>-->
            <#--<li>-->
            <#--&lt;#&ndash;<!--遍历所有导航页码，如果遍历的页码页当前页码相等就高亮显示，如果相等就普通显示  &ndash;&gt;&ndash;&gt;-->
            <#--<#list pageInfo.navigatepageNums as pageNums>-->
                <#--<#if pageNums==pageInfo.pageNum>-->
            <#--<li class="active"><a href="#">${pageNums}</a></li>-->
                <#--</#if>-->
                <#--<#if pageNums!=pageInfo.pageNum>-->
                <#--<li >-->
                    <#--<a href="${rc.contextPath}/adminController/adminSeeAllPapersBypage?pageIndex=${pageNums}&keywords=${keywords!}">${pageNums}</a>-->
                <#--</li>-->
                <#--</#if>-->
            <#--</#list>-->
                <#--</li>-->
            <#--&lt;#&ndash;<!-- 如果还有后页就访问当前页码+1的页面， &ndash;&gt;&ndash;&gt;-->
                    <#--<#if pageInfo.hasNextPage>-->
                        <#--<li>-->
                            <#--<a href="${rc.contextPath}/adminController/adminSeeAllPapersBypage?pageIndex=${pageInfo.pageNum+1}&keywords=${keywords!}" aria-label="Next">-->
                                <#--<span aria-hidden="true">下一页</span>-->
                            <#--</a>-->
                        <#--</li>-->
                    <#--</#if>-->
                <#--<li>-->
                    <#--<a href="${rc.contextPath}/adminController/adminSeeAllPapersBypage?pageIndex=${pageInfo.pages}&keywords=${keywords!}">末页</a>-->
                <#--</li>-->
            <#--</ul>-->
            <#--<div class="xyp">-->
                <#--<input type="number" name="jumpInput" id="jumpInput"/>-->
                <#--<input type="button" class="btn-primary" value="跳转" onclick="jump()"/>-->
            <#--</div>-->
        <#--</nav>-->
    <#--</div>-->
    <#include "../pageHelper2.ftl"/>
</div>

</body>
</html>