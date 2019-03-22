package com.roy.controller;

import com.roy.model.Chat;
import com.roy.model.Student;
import com.roy.model.Teacher;
import com.roy.service.MessageService;
import com.roy.service.StudentService;
import com.roy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Intent: webSocket服务器
 */
@Controller
@ServerEndpoint(value = "/webSocket/chat/{roomName}/{userId}/{role}")
public class WSController {

    //https://blog.csdn.net/m0_37202351/article/details/86255132   解决 websocket 不能注入( @Autowired )

    //  这里使用静态，让 service 属于类
    private static MessageService messageService;
    private static TeacherService teacherService;
    private static StudentService studentService;

    // 注入的时候，给类的 service 注入
    @Autowired
    public void setMessageService(MessageService messageService) {
        WSController.messageService = messageService;
    }
    // 注入的时候，给类的 service 注入
    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        WSController.teacherService = teacherService;
    }
    // 注入的时候，给类的 service 注入
    @Autowired
    public void setStudentService(StudentService studentService) {
        WSController.studentService = studentService;
    }

    // 使用map来收集session，key为roomName，value为同一个房间的用户集合
    // concurrentMap的key不存在时报错，不是返回null
    private static final Map<String, Set<Session>> rooms = new ConcurrentHashMap();

    private static Chat chat = new Chat();

    @OnOpen
    public void connect(@PathParam("roomName") String roomName, Session session){

        // 将session按照房间名来存储，将各个房间的用户隔离
        if (!rooms.containsKey(roomName)) {
            // 创建房间不存在时，创建房间
            Set<Session> room = new HashSet<>();
            // 添加用户
            room.add(session);
            rooms.put(roomName, room);
        } else {
            // 房间已存在，直接添加用户到相应的房间
            rooms.get(roomName).add(session);
        }
        System.out.println("onOpen:::" + session.getId() + "连接到服务器");
    }

    @OnClose
    public void disConnect(@PathParam("roomName") String roomName, Session session) {
        rooms.get(roomName).remove(session);
        System.out.println("onClose:::" + session.getId() + "断开了连接");
    }

    @OnMessage
    public void receiveMsg(@PathParam("roomName") String roomName,
                           @PathParam("userId") Long userId,
                           @PathParam("role") String role,
                           String msg) throws Exception {

        // 记录聊天消息
        String userName = "游客";
        chat.setTcid(Long.valueOf(roomName));
        chat.setSendId(userId);
        if("teacher".equals(role)){//教师
            Teacher teacher = teacherService.getTeacByTeacId(userId);
            chat.setAttach("1");
            userName = teacher.getTeacName();
        }else if("student".equals(role)){//教师
            Student student = studentService.getStudentById(userId);
            chat.setAttach("2");
            userName =student.getStuName();
        }else {
            try {
                this.onError(new Throwable());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        chat.setSendName(userName);
        chat.setMessage(msg);
        boolean result = false;
        if(messageService != null) {
            System.out.println(chat);
            result = messageService.addChat(chat);
        }else{
            System.out.println("messageService is null");
        }
        if(result){
            msg = userName + ":" + msg;
            System.out.println(msg);
            // 接收到信息后进行广播
            broadcast(roomName, msg);
        }else {
            System.out.println("消息发送失败");
        }

    }
    /**
     * 发生错误是调用方法
     * @param t
     * @throws Throwable
     */
    @OnError
    public void onError(Throwable t) throws Throwable {
        System.out.println("错误: " + t.toString());
    }

    // 按照房间名进行广播
    public static void broadcast(String roomName, String msg) throws Exception {
        for (Session session : rooms.get(roomName)) {
            session.getBasicRemote().sendText(msg);
        }
    }


}