package com.caesar.controller;

import com.caesar.assistant.XiaoxiaAgent;
import com.caesar.domain.dto.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/17
 */



@Tag(name = "郑可大王")
@RestController
@RequestMapping("/xiaoxia")
public class XiaoxiaController {

    @Autowired
    private XiaoxiaAgent xiaoxiaAgent;

    @Operation(summary = "郑可大王的对话")
    @PostMapping("/chat")
    public String chat(@RequestBody ChatForm chatForm) {

        return xiaoxiaAgent.chat(chatForm.getMemoryId(),chatForm.getContent());

    }


}
