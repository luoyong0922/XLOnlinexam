<html>
<head>
    <title>我的课程</title>
    <link href="${rc.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
     <link href="${rc.contextPath}/static/css/chat.css" rel="stylesheet">
    <script src="${rc.contextPath}/static/js/jquery-1.11.0.js"></script>
    <script src="${rc.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="margin-top: 40px;min-width: 514px;">
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
               <a style="display: block;cursor: pointer;"onclick="initWebSocket(${courseManage.id},'${courseManage.courseName!}',${Session["id"]!},'${Session["role"]!}','${Session["name"]!}')">参与讨论</a>
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
<div class="msg_board" id="msg_board"></div>
<div style="background:pink;margin: 26px 0;min-width:115px; position: fixed;">
<img style="margin-left: 30%;width:50px;height:50px;"/>
          <div style="margin:auto;width:115px;">
            <div class="">
              查看聊天记录
            </div>
            <select>
              <option >最近一天</option>
              <option >最近三天</option>
              <option>最近一周</option>
			  <option>最近两周</option>
			  <option>最近一个月</option>
			  <option>最近三个月</option>
			  <option>最近一年</option>
			  <option>所有记录</option>
            </select>
          </div>
<img style="margin-left: 30%;width:50px;height:50px;"/>
</div>
<input type="button" value="发送" onclick="send_msg('${Session["name"]!}')" style="float:right;margin-left:10px; background: #00FF94; margin-bottom: 10px;clear:both;"/>
<div  id="input_msg" contenteditable="true" style="width:62%; float:right; min-width:170px; min-height: 31px; margin-right:0px;  border: 0.2px solid #0F5;"></div>
</div>
</div>
<script>
        var webSocket;
        function send_msg(username) {
            if (webSocket != null) {
                var input_msg = $("#input_msg").text().trim();
                if (input_msg == "") {
                    return;
                }
                webSocket.send(username + "`" + input_msg);
                // 清空div框里的信息
                $("#input_msg").html("");
            } else {
                alert("您已掉线，请重新进入聊天室...");
            }
        };

        function closeWs() {
            webSocket.close();
        };

        function initWebSocket(teacCourseId,courseName,userId,role,username) {
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
                    var received_msg = evt.data;
                    var name = received_msg.split("\\")[0];
                    var message = received_msg.substr(name.length+2);
                    var html = '';
                    if( name == username){
                        html += '<div class="receiver">';
                        html += '<div class="name">' + name + '</div>';
                        html += '<div>';
                        html += '<div class="right_triangle"></div>';
                        html += '<span>'+ message + '</span>';
                        html += '</div>';
                        html += '</div>';
                     }else{
                         html += '<div class="sender">';
                         html += '<div class="name">' + name + '</div>';
                         html += '<div>';
                         html += '<div class="left_triangle"></div>';
                         html += '<span>'+ message + '</span>';
                         html += '</div>';
                         html += '</div>';
                     }
                    $('#msg_board').append(html);
                    // 清空div框里的信息
                    $("#input_msg").html("");
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