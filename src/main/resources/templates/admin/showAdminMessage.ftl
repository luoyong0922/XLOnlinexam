<html>
<head>
    <title>管理员信息</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
<input type="hidden" name="id" value="${admin.id }"/>
<table class="table table-hover center-block" style="margin-top: 80px;width: 400px;">
    <tr>
        <td>电话</td>
        <td><input type="text" name="adminPhone" value="${admin.adminPhone}" /></td>
    </tr>
    <tr>
        <td>密码</td>
        <td><input type="password" name="adminPassword" value="${admin.adminPassword}" /></td>
    </tr>
    <tr>
        <td>姓名</td>
        <td><input type="text" name="adminName" value="${admin.adminName}" /></td>
    </tr>
    <tr>
        <td>性别</td>
        <td>
            <input type="radio" name="adminGender" value="男" id="male">男
            <input type="radio" name="adminGender" value="女" id="female">女
        </td>
    </tr>
    <tr>
        <td>密保问题</td>
        <td><input type="text" name="adminQuestion" value="${admin.adminQuestion}" /></td>
    </tr>
    <tr>
        <td>密保回答</td>
        <td><input type="text" name="adminKey" value="${admin.adminKey}" /></td>
    </tr>
    <#--<tr>-->
        <#--<td colspan="2">-->
            <#--<a href="${rc.contextPath}/adminController/toAdminIndex">返回</a>-->
            <#--<a href="${rc.contextPath}/adminController/toModifyAdminMsg/${admin.adminPhone}">修改密码</a>-->
        <#--</td>-->
    <#--</tr>-->
</table>
</div>
<script>
    window.onload=function(){
        var gender='${admin.adminGender}';
        if(gender==='男'){
            document.getElementById('male').checked=true;
        }else{
            document.getElementById('female').checked=true;
        }

    }

</script>
</body>
</html>