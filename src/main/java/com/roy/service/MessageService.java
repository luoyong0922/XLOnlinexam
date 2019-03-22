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

}
