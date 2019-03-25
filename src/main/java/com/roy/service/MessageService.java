package com.roy.service;

import com.roy.model.Chat;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface MessageService {

    Chat getMessageById(Long id);

    //新增聊天记录
    public boolean addChat(Chat chat);
    //查询所有聊天记录
    public List<Chat> getChatList(Long tcId);
    //查询今天的聊天记录
    public List<Chat> getTodayChatList(Long tcId);
    //查询最近三天聊天记录
    public List<Chat> getRecentThreeDaysChatList(Long tcId);
    //查询最近一周聊天记录
    public List<Chat> getRecentWeekChatList(Long tcId);
    //查询最近两周聊天记录
    public List<Chat> getRecentTwoWeeksChatList(Long tcId);
    //查询本月聊天记录
    public List<Chat> getCurrentMouthChatList(Long tcId);
    //查询近三个月聊天记录
    public List<Chat> getRecentThreeMouthsChatList(Long tcId);
    //查询今年聊天记录
    public List<Chat> getCurrentYearChatList(Long tcId);

}
