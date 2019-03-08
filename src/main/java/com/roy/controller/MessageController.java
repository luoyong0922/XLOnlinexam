package com.roy.controller;
import com.roy.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/messageController")
public class MessageController {
    @Resource
    private MessageService messageService;

}
