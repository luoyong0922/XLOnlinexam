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

    <form action="${rc.contextPath}/adminController/getAllStudentsByPage?pageIndex=1" method="post">
        搜索关键词:<input type="text" name="keywords" value="${keywords}"/>
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
                <th>姓名</th>
                <th>住址</th>
                <th>性别</th>
                <th>年级</th>
                <th>班级</th>
                <th>电话</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>

         <#list pageInfo.list?sort_by("stuState") as student>
         <tr <#if student.stuState == 2>class="table-warning"<#elseif student.stuState == 1>class="table-info" </#if>>
         <#--<tr class="table-info">-->
             <td>${student?counter}</td>
             <td>${student.stuNum}</td>
             <!--<td>${student.stuPassword}</td>-->
              <td>涉及用户敏感信息，不予显示</td>
             <td>${student.stuName}</td>
             <td>${student.stuAddress}</td>
             <td>${student.stuGender}</td>
             <td>${student.stuGrade}</td>
             <td>${student.stuClass}</td>
             <td>${student.stuPhone}</td>
             <td>
             <#if student.stuState == 2>待审核<#elseif student.stuState == 1>已审核 <#else>被禁用 </#if>
             </td>
             <td>
                 <a href="${rc.contextPath}/adminController/studentUpdate/${student.id}">修改</a>
                 <a href="javascript:deleteStudent('${student.id }','${student.stuName}')">删除</a>
             </td>
         </tr>
         </#list>

            </tbody>
        </table>
        </div>
        <br/>
        当前是第${pageInfo.pageNum}页,共${pageInfo.pages}页，总记录数为${pageInfo.total}条
        <br/>



        <div class="col-md-12">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li>
                        <a href="${rc.contextPath}/adminController/getAllStudentsByPage?pageIndex=1&keywords=${keywords}">首页</a>
                    </li>
                <#--<!-- 如果还有前页就访问当前页码-1的页面， &ndash;&gt;-->
                    <#if pageInfo.hasPreviousPage>
                        <li>
                            <a href="${rc.contextPath}/adminController/getAllStudentsByPage?pageIndex=${pageInfo.pageNum-1}&keywords=${keywords}" aria-label="Previous">
                                <span aria-hidden="true">上一页</span>
                            </a>
                        </li>
                   </#if>
                <li>
                <#--<!--遍历所有导航页码，如果遍历的页码页当前页码相等就高亮显示，如果相等就普通显示  &ndash;&gt;-->
            <#list pageInfo.navigatepageNums as pageNums>
                <#if pageNums==pageInfo.pageNum>
            <li class="active"><a href="#">${pageNums}</a></li>
                </#if>
                <#if pageNums!=pageInfo.pageNum>
                <li >
                    <a href="${rc.contextPath}/adminController/getAllStudentsByPage?pageIndex=${pageNums}&keywords=${keywords}">${pageNums}</a>
                </li>
                </#if>
            </#list>
                    </li>
                <#--<!-- 如果还有后页就访问当前页码+1的页面， &ndash;&gt;-->
                    <#if pageInfo.hasNextPage>
                        <li>
                            <a href="${rc.contextPath}/adminController/getAllStudentsByPage?pageIndex=${pageInfo.pageNum+1}&keywords=${keywords}" aria-label="Next">
                                <span aria-hidden="true">下一页</span>
                            </a>
                        </li>
                    </#if>
                    <li>
                        <a href="${rc.contextPath}/adminController/getAllStudentsByPage?pageIndex=${pageInfo.pages}&keywords=${keywords}">末页</a>
                    </li>
                </ul>
                <div class="xyp">
                    <input type="number" name="jumpInput" id="jumpInput"/>
                    <input type="button" class="btn-primary" value="跳转" onclick="jump()"/>
                </div>
            </nav>
        </div>

</div>
<script>
    function deleteStudent(id,stuName) {
        if(confirm('确定要删除' + stuName + '学生吗?')){
            window.location='${rc.contextPath}/adminController/deleteStudent?id='+id;
        }
    }

    function jump() {
        var jumpPage=$('#jumpInput').val();
        window.location='${rc.contextPath}/adminController/getAllStudentsByPage?pageIndex='+jumpPage+'&keywords=${keywords}';
    }
    function addStudent() {
        window.location='${rc.contextPath}/adminController/addstudent';
    }

    function  deletes() {
        var f=document.forms[1];
        var subcks=document.getElementsByName("ids");
        var str="";
        for(var i=0;i<subcks.length;i++){
            if(subcks[i].checked==true) {
                str+=subcks[i].value + ",";
            }
        }
        str = str.substring(0,str.length-1);
        if(str==''){
            alert("请选择要删除的学生");
        }else{
            if(confirm('确定要删除这些学生吗？')){
                f.action='deletes?id='+str;
                f.submit();
            }
        }
    }
</script>
</body>
</html>