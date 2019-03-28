<!-- 引入标签-->
<#assign  sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<html>
<head>
    <title>作业通知</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
    <style>
        table {
        table-layout: fixed;
        }
        td {
        overflow: hidden; /*自动隐藏文字*/
        text-overflow: ellipsis;/*文字隐藏后添加省略号*/
        white-space: nowrap;/*强制不换行*/
        width: 15em;/*不允许出现半汉字截断*/
        }
    </style>
</head>
<body>
<div class="container center-block" style="margin-top: 50px;">
<div style="text-align:center;font-size: 24px;">
    课程名称：<b>${courseName!}</b>
    <!--  权限控制：只有老师才有权限新增作业  -->
     <@sec.authorize access="hasRole('ROLE_TEACHER')">
        <input type="button" class="btn btn-success" style="float:right;" value="新增作业" onclick="toAddHomeWork()"/>
     </@sec.authorize>
</div><br/>
    <table class="table table-hover" id="Test">
        <thead>
        <tr>
            <th style="width: 100px;">序号</th>
            <th>作业内容</th>
            <th>布置时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#if (pageInfo.total > 0)>
        <#list pageInfo.list as homeWork>
        <tr>
            <td>${homeWork?counter}</td>
            <td>${homeWork.homeworkContent}</td>
            <td>${homeWork.date?string("yyyy-MM-dd")}</td>
            <td>
                <input type="button" class="btn btn-info" value="查看详情"
                        onclick="toShowHomeWork(${homeWork.id})"/>
            </td>
        </tr>
        </#list>
        <#else>
        <tr style="text-align:center"><td colspan="4">暂无数据</td></tr>
        </#if>
        </tbody>
    </table>
 </div>

 <!-- 查看作业模态框 -->

 <div class="modal fade" id="ShowHomeWork" tabindex="-1" role="dialog"
      aria-labelledby="myModalLabel">
     <div class="modal-dialog" role="document" style="z-index: inherit;">
         <div class="modal-content">
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal"
                         aria-label="Close">
                     <span aria-hidden="true">&times;</span>
                 </button>
                 <h4 class="modal-title" id="myModalLabel">作业</h4>
             </div>
             <div class="modal-body">
                 <form class="form-horizontal" id="user">
                     <div class="form-group">
                         <label for="courseName" class="col-sm-4 control-label">
                             课程名称：</label>
                         <div class="col-sm-8">
                             <input type="text" class="form-control" id="courseName" name="courseName" readonly="true"/>
                         </div>
                     </div>
                     <div class="form-group">
                         <label for="date" class="col-sm-4 control-label">
                             布置时间：</label>
                         <div class="col-sm-8">
                             <input type="text" class="form-control" id="date" name="date"  readonly="true"/>
                         </div>
                     </div>
                     <div class="form-group">
                          <label for="content" class="col-sm-4 control-label">
                              作业内容：</label>
                          <div class="col-sm-8">
                              <textarea rows="6" class="form-control" cols="80" id="content" name="content"  readonly="true"></textarea>
                          </div>
                      </div>
                 </form>
             </div>
         </div>
     </div>
 </div>

 <!-- 添加作业模态框 -->
 <div class="modal fade" id="AddHomeWork" role="dialog"
      aria-labelledby="myModalLabel">
     <div class="modal-dialog" role="document" style="z-index: inherit;">
         <div class="modal-content">
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal"
                         aria-label="Close">
                     <span aria-hidden="true">&times;</span>
                 </button>
                 <h4 class="modal-title" id="myModalLabel">布置作业</h4>
             </div>
             <div class="modal-body">
                 <form class="form-horizontal" id="add" action="${rc.contextPath}/homeworkController/publicHomeWork" method="post" style="line-height: 1em;">
                      <input type="text" name="teacCourseId" value="${teaccourseId}" style="display: none;">
                     <div class="form-group">
                          <label for="courseName" class="col-sm-4 control-label">
                              课程名称：</label>
                          <div class="col-sm-8">
                              <input type="text" class="form-control" id="courseName" name="courseName" value="${courseName}" readonly="true"/>
                          </div>
                      </div>
                      <div class="form-group">
                           <label for="homeworkContent" class="col-sm-4 control-label">
                               作业内容：</label>
                           <div class="col-sm-8">
                               <textarea class="form-control" rows="6"  cols="80" id="homeworkContent" name="homeworkContent"></textarea>
                           </div>
                       </div>
                     <button type="submit" class="btn btn-primary center-block" style="width:80%;">提交</button>
                 </form>
             </div>
         </div>
     </div>
 </div>

        <#if (pageInfo.total > 6)>
            <#include "../pageHelper2.ftl"/>
        </#if>
</div>
<script>
// 老师新增作业
  function toAddHomeWork() {
   $("#AddHomeWork").modal("show");
  }

// 通过id获取试题信息
    function toShowHomeWork(homeworkId) {
     $("#ShowHomeWork").modal("show");

        $.ajax({
            type:"post",
            dataType: "json",
            contentType:"application/json",
            url:"${rc.contextPath}/homeworkController/showHomework/"+homeworkId,
            success:function(data) {
                var homework = data.obj;
                $("#content").val(homework.homeworkContent);
                var unixTimestamp = new Date( homework.date ) ;
                commonTime = unixTimestamp.toLocaleString();
                $("#date").val(commonTime);
                $("#courseName").val(homework.courseName);
            }
        });
    }
    /* 自定义时间格式 */
    Date.prototype.toLocaleString = function() {
            let year = this.getFullYear();
            let month = (this.getMonth()+1 < 10 ? '0'+ (this.getMonth()+1) : this.getMonth()+1 );
            let date = (this.getDate() < 10 ? '0'+ this.getDate() : this.getDate() );
            let hour = this.getHours() + 8;  // 时区不一致？？
            let minute = (this.getMinutes() < 10 ? '0'+ this.getMinutes() : this.getMinutes() );
            let second = (this.getSeconds() < 10 ? '0'+ this.getSeconds() : this.getSeconds() );
            return year + "/" + month + "/" + date + " " + hour + ":" + minute + ":" + second;
        };
</script>
</body>
</html>