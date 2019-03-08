<html>
<head>
    <title>分页查询学生</title>
</head>
<body>
<form action="${rc.contextPath}/adminController/getAllStudentsByPage?pageIndex=1" method="post">
    搜索关键词:<input type="text" name="keywords" value="${keywords}"/>
    <input type="submit" value="搜索">
    <input type="button" value="添加学生" onclick="javascript:addStudent()"/>
</form>
<form method="post">
    <table width="300px" border="1px" cellspacing="0">
        <tr>
            <th>序号</th>
            <th>id</th>
            <th>学号</th>
            <th>密码</th>
            <th>名字</th>
            <th>地址</th>
            <th>性别</th>
            <th>年级</th>
            <th>班级</th>
            <th>电话</th>
            <th>操作</th>
        </tr>
    <#list pageInfo.list as student>
        <tr>
            <td>${student?counter}</td>
            <td>${student.id}</td>
            <td>${student.stuNum}</td>
            <td>${student.stuPassword}</td>
            <td>${student.stuName}</td>
            <td>${student.stuAddress}</td>
            <td>${student.stuGender}</td>
            <td>${student.stuGrade}</td>
            <td>${student.stuClass}</td>
            <td>${student.stuPhone}</td>
            <td>
                <a href="studentUpdate/${student.id}">修改</a>
                <a href="javascript:deleteStudent('${student.id }','${student.stuName}')">删除</a>
            </td>
        </tr>
    </#list>
    </table>
</form>
<#--<input type="button" onclick="deletes()" value="批量删除">-->
<br/>
当前是第${pageInfo.pageNum}页,共${pageInfo.pages}页，总记录数为${pageInfo.total}条
<br/>
<input type="text" name="jumpInput" value="" id="jumpInput"/>
<input type="button" value="跳转" onclick="jump()"/>


<div class="col-md-6">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <a href="${rc.contextPath}/adminController/getAllStudentsByPage?pageIndex=1&keywords=${keywords}">首页</a>
            </li>
            <#--<!-- 如果还有前页就访问当前页码-1的页面， &ndash;&gt;-->
            <c:if test="${pageInfo.hasPreviousPage}">
                <li>
                    <a href="${rc.contextPath}/adminController/getAllStudentsByPage?pageIndex=${pageInfo.pageNum-1}&keywords=${keywords}" aria-label="Previous">
                        <span aria-hidden="true">上一页</span>
                    </a>
                </li>
            </c:if>
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
            <c:if test="${pageInfo.hasNextPage}">
                <li>
                    <a href="${rc.contextPath}/adminController/getAllStudentsByPage?pageIndex=${pageInfo.pageNum+1}&keywords=${keywords}" aria-label="Next">
                        <span aria-hidden="true">下一页</span>
                    </a>
                </li>
            </c:if>
            <li>
                <a href="${rc.contextPath}/adminController/getAllStudentsByPage?pageIndex=${pageInfo.pages}&keywords=${keywords}">末页</a>
            </li>
        </ul>
    </nav>
</div>




<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
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
            alert("请选择要删除的用户");
        }else{
            if(confirm('确定要删除这些用户吗？')){
                f.action='deletes?id='+str;
                f.submit();
            }
        }
    }
</script>

</body>
</html>