package com.caesar.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/17
 */
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemoryProvider = "chatMemoryProviderXiaoxia",
        tools = "appointmentTools",
        contentRetriever = "contentRetrieverXiaoxiaPinecone" // 配置一下 从那里拿到这个向量存储
)
public interface XiaoxiaAgent {

    @SystemMessage(fromResource = "xiaoxia_prompt.txt")
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);

}
