<html>
<head>
    <title>我的课程</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<style type="text/css">
      .msg_board {
          margin: 25px auto;
  		width: 90%;
  		min-height: 150px;
          border: solid 1px darkcyan;
          overflow-y: scroll;
          // 文字长度大于div宽度时换行显示
          word-break: break-all;
      }
      /*set srcoll start*/
      ::-webkit-scrollbar
      {
          width: 10px;
          height: 10px;
          background-color: #D6F2FD;
      }
      ::-webkit-scrollbar-track
      {
          -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
          /*border-radius: 5px;*/
          background-color: #D6F2FD;
      }
      ::-webkit-scrollbar-thumb
      {
          height: 20px;
          /*border-radius: 10px;*/
          -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
          background-color: #89D7F7;
      }
      /*set srcoll end*/
</style>
<body>
<div class="container" style="margin-top: 40px;">
<div id="courseInfo" style="display:block">
    <table class="table table-hover" id="Test">
        <tr>
            <th>课程名称</th>
            <th>授课老师</th>
            <th>开课时间</th>
            <th>结课时间</th>
            <th>课程类型</th>
            <th>课程学分</th>
            <th style="text-align: center;">操作</th>
        </tr>
        </thead>
        <tbody>
    <#list pageInfo.list as courseManage>
        <tr>
            <td style="display: none" id="teaccourseid">${courseManage.id!}</td>
            <td>${courseManage.courseName!}</td>
            <td>${courseManage.teacName!}</td>
            <td>${courseManage.startTime?string("yyyy-MM-dd")}</td>
            <td>${courseManage.endTime?string("yyyy-MM-dd")}</td>
            <td>${courseManage.courseType!}</td>
            <td>${courseManage.courseCredit!}</td>
            <td style="text-align: center;">
               <a style="display: block;cursor: pointer;"onclick="initWebSocket(${courseManage.id},'${courseManage.courseName!}',${Session["id"]!},'${Session["role"]!}')">参与讨论</a>
            </td>
        </tr>
    </#list>
    </tbody>
    </table>
    <#if (pageInfo.total > 6)>
        <#include "../pageHelper2.ftl"/>
    </#if>
</div>
<div id="chatting" style="display:none; font-size:18px;">
<label>课程：</label>
<input id="input_roomName" width="30%" readonly="true">
<input type="button" value="退出聊天室" onclick="closeWs()" style="float:right;" />
<div class="msg_board"></div>
<input type="button" value="发送" onclick="send_msg()" style="float:right;margin-right:5%; background: #00FF94;"/>
<div  id="input_msg" contenteditable="true" style="width:82%; float:right; min-width:170px; min-height: 31px; margin-right:6px;  border: 0.2px solid #0F5;"></div>
</div>
</div>
<script>
        var webSocket;

        function send_msg() {
            if (webSocket != null) {
                var input_msg = $("#input_msg").text().trim();
                if (input_msg == "") {
                    return;
                }
                webSocket.send(input_msg);
                // 清空div框里的信息
                $("#input_msg").html("");
            } else {
                alert("您已掉线，请重新进入聊天室...");
            }
        };

        function closeWs() {
            webSocket.close();
        };

        function initWebSocket(teacCourseId,courseName,userId,role) {

            // 房间名不能为空
            if (teacCourseId == null || teacCourseId == "") {
                alert("找不到该讨论组");
                return;
            }

            if ("WebSocket" in window) {

                if (webSocket == null) {
                    var url = "ws://localhost:8080/webSocket/chat/" + teacCourseId + "/" + userId + "/" + role;
                    // 打开一个 web socket
                    webSocket = new WebSocket(url);
                } else {
                    alert("您已进入聊天室...");
                }

                webSocket.onopen = function () {
                    $("#input_roomName").val(courseName);
                    $("#courseInfo").css("display","none");
                    $("#chatting").css("display","block");

                    alert("已进入聊天室，开始畅聊吧...");
                };

                webSocket.onmessage = function (evt) {
                    var msg_board = document.getElementsByClassName("msg_board")[0];
                    var received_msg = evt.data;
                    var old_msg = msg_board.innerHTML;
                    msg_board.innerHTML = old_msg + received_msg + "<br>";
                    // 让滚动块往下移动
                    msg_board.scrollTop = msg_board.scrollTop + 40;
                };

                webSocket.onclose = function () {
                    // 关闭 websocket，清空信息板
                    alert("连接已断开...");
                    $("#input_msg").html("");
                    $("#courseInfo").css("display","block");
                    $("#chatting").css("display","none");
                    webSocket = null;
                    document.getElementsByClassName("msg_board")[0].innerHTML = "";
                };
            }
            else {
                // 浏览器不支持 WebSocket
                alert("您的浏览器不支持 WebSocket!");
            }
        }
</script>

</body>
</html>