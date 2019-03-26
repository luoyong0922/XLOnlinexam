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
        chatExample.setOrderByClause("send_time");
        ChatExample.Criteria criteria = chatExample.createCriteria();
        criteria.andTcidEqualTo(tcId);

        return chatDao.selectByExample(chatExample);
    }

    @Override
    public List<Chat> getTodayChatList(Long tcId) {
        return chatDao.selectTodayMsg(tcId);
    }

    @Override
    public List<Chat> getRecentThreeDaysChatList(Long tcId) {
        return chatDao.selectRecentThreeDaysMsg(tcId);
    }

    @Override
    public List<Chat> getRecentWeekChatList(Long tcId) {
        return chatDao.selectRecentWeekMsg(tcId);
    }

    @Override
    public List<Chat> getRecentTwoWeeksChatList(Long tcId) {
        return chatDao.selectRecentTwoWeeksMsg(tcId);
    }

    @Override
    public List<Chat> getCurrentMouthChatList(Long tcId) {
        return chatDao.selectCurrentMouthMsg(tcId);
    }

    @Override
    public List<Chat> getRecentThreeMouthsChatList(Long tcId) {
        return chatDao.selectRecentThreeMouthsMsg(tcId);
    }

    @Override
    public List<Chat> getCurrentYearChatList(Long tcId) {
        return chatDao.selectCurrentYearMsg(tcId);
    }
}
