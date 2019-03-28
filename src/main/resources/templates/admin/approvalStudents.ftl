<!DOCTYPE html>
<html>
<head>
    <title>所有学生列表</title>
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

    <h2 style="text-align: center">学生列表</h2>

    <form action="${rc.contextPath}/adminController/toApprovalStudents" method="post">
        搜索关键词:<input type="text" name="keywords" value="${keywords!}" placeholder="请输入学生姓名"/>
        <input type="submit" class="btn-primary" value="搜索">
        <input type="button" class="btn-success" value="添加学生" onclick="javascript:addStudent()"/>
    </form>

        <div class="container">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>序号</th>
                <th>学号</th>
                <th>密码</th>
                <th>名字</th>
                <th>住址</th>
                <th>性别</th>
                <th>年级</th>
                <th>班级</th>
                <th>电话</th>
                <th>状态</th>
                <th style="text-align: center">操作</th>
            </tr>
            </thead>
            <tbody>

         <#list pageInfo.list as student>
         <tr <#if student.stuState == 2>class="table-warning"<#elseif student.stuState == 1>class="table-info" </#if>>
             <td>${student?counter}</td>
             <td>${student.stuNum}</td>
             <td>......</td>
             <td>${student.stuName}</td>
             <td>${student.stuAddress}</td>
             <td>${student.stuGender}</td>
             <td>${student.stuGrade}</td>
             <td>${student.stuClass}</td>
             <td>${student.stuPhone}</td>
             <td>待审核</td>
             <td style="text-align: center">
                 <a style="cursor: pointer;" onclick="approval('${student.id}',2)">通过审核</a><br>
                 <a style="cursor: pointer;" onclick="approval('${student.id}',0)">未通过审核</a>
             </td>
         </tr>
         </#list>

            </tbody>
        </table>
        </div>
        <br/>
        <#if (pageInfo.total > 0)>
        当前是第${pageInfo.pageNum}页,共${pageInfo.pages}页，总记录数为${pageInfo.total}条
        <br/>



        <div class="col-md-12">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li>
                        <a href="${rc.contextPath}/adminController/toApprovalStudents?keywords=${keywords!}">首页</a>
                    </li>
                <#-- 如果还有前页就访问当前页码-1的页面，-->
                    <#if pageInfo.hasPreviousPage>
                        <li>
                            <a href="${rc.contextPath}/adminController/toApprovalStudents?pageIndex=${pageInfo.pageNum-1}&keywords=${keywords!}" aria-label="Previous">
                                <span aria-hidden="true">上一页</span>
                            </a>
                        </li>
                   </#if>
                <li>
                <#--遍历所有导航页码，如果遍历的页码页当前页码相等就高亮显示，如果相等就普通显示 -->
            <#list pageInfo.navigatepageNums as pageNums>
                <#if pageNums==pageInfo.pageNum>
            <li class="active"><a href="#">${pageNums}</a></li>
                </#if>
                <#if pageNums!=pageInfo.pageNum>
                <li >
                    <a href="${rc.contextPath}/adminController/toApprovalStudents?pageIndex=${pageNums}&keywords=${keywords!}">${pageNums}</a>
                </li>
                </#if>
            </#list>
                    </li>
                <#--<!-- 如果还有后页就访问当前页码+1的页面，-->
                    <#if pageInfo.hasNextPage>
                        <li>
                            <a href="${rc.contextPath}/adminController/toApprovalStudents?pageIndex=${pageInfo.pageNum+1}&keywords=${keywords!}" aria-label="Next">
                                <span aria-hidden="true">下一页</span>
                            </a>
                        </li>
                    </#if>
                    <li>
                        <a href="${rc.contextPath}/adminController/toApprovalStudents?pageIndex=${pageInfo.pages}&keywords=${keywords!}">末页</a>
                    </li>
                </ul>
                <div class="xyp">
                    <input type="number" name="jumpInput" id="jumpInput"/>
                    <input type="button" class="btn-primary" value="跳转" onclick="jump()"/>
                </div>
            </nav>
        </div>
        <#else>
        <div style="font-size:18px;text-align:center;">暂无数据</div>
        </#if>
</div>
<script>

    function jump() {
        var jumpPage=$('#jumpInput').val();
        window.location='${rc.contextPath}/adminController/toApprovalStudents?pageIndex='+jumpPage+'&keywords=${keywords!}';
    }
    function addStudent() {
        window.location='${rc.contextPath}/adminController/addstudent';
    }

    function approval(ids,op) {
        window.location='${rc.contextPath}/adminController/doApprovalStudents/'+ids+'/'+op;
    }
</script>
</body>
</html>