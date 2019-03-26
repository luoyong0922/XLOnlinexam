package com.roy.controller;
import com.roy.model.Chat;
import com.roy.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/messageController")
public class MessageController {
    @Resource
    private MessageService messageService;

    @GetMapping("/getTodayMsg")
    public List<Chat> getTodayMsg(@RequestParam("tcId") Long tcId){
        return messageService.getTodayChatList(tcId);
    }

    @GetMapping("/getRecentThreeDaysMsg")
    public List<Chat> getRecentThreeDaysMsg(@RequestParam("tcId") Long tcId){
        return messageService.getRecentThreeDaysChatList(tcId);
    }

    @GetMapping("/getRecentWeekMsg")
    public List<Chat> getRecentWeekMsg(@RequestParam("tcId") Long tcId){
        return messageService.getRecentWeekChatList(tcId);
    }

    @GetMapping("/getRecentTwoWeeksMsg")
    public List<Chat> getRecentTwoWeeksMsg(@RequestParam("tcId") Long tcId){
        return messageService.getRecentTwoWeeksChatList(tcId);
    }

    @GetMapping("/getCurrentMouthMsg")
    public List<Chat> getCurrentMouthMsg(@RequestParam("tcId") Long tcId){
        return messageService.getCurrentMouthChatList(tcId);
    }

    @GetMapping("/getRecentThreeMouthsMsg")
    public List<Chat> getRecentThreeMouthsMsg(@RequestParam("tcId") Long tcId){
        return messageService.getRecentThreeMouthsChatList(tcId);
    }

    @GetMapping("/getCurrentYearMsg")
    public List<Chat> getCurrentYearMsg(@RequestParam("tcId") Long tcId){
        return messageService.getCurrentYearChatList(tcId);
    }

    @GetMapping("/getAllMsg")
    public List<Chat> getAllMsg(@RequestParam("tcId") Long tcId){
        return messageService.getChatList(tcId);
    }


}
