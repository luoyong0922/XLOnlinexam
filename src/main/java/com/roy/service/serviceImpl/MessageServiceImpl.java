package com.roy.service.serviceImpl;

import com.roy.mapper.ChatMapper;
import com.roy.model.Chat;
import com.roy.model.ChatExample;
import com.roy.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Resource
    ChatMapper chatDao;

    @Override
    public Chat getMessageById(Long id) {
        return chatDao.selectByPrimaryKey(id);
    }

    @Override
    public boolean addChat(Chat chat) {
        return ( chatDao.insertSelective(chat) > 0 );
    }

    @Override
    public List<Chat> getChatList(Long tcId) {
        ChatExample chatExample = new ChatExample();
        ChatExample.Criteria criteria = chatExample.createCriteria();
        criteria.andTcidEqualTo(tcId);
        return chatDao.selectByExample(chatExample);
    }
}
