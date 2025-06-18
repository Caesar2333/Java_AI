package com.caesar.config;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/17
 */
@Configuration
public class MemoryChatConfig {

    @Bean
    public ChatMemory chatMemory() {

        // 这里的bean的名字就是 方法的名字 也就是 chatMemory的
        // 这里设置了bean之后 AIServices中就可以使用了
        // 这里设置的是 message聊天记录的数量
        return MessageWindowChatMemory.withMaxMessages(10);

    }




}
