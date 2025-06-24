package com.caesar.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/17
 */
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        streamingChatModel = "qwenStreamingChatModel",
        chatMemoryProvider = "chatMemoryProviderXiaoxia",
        tools = "appointmentTools",
        contentRetriever = "contentRetrieverXiaoxiaPinecone" // 配置一下 从那里拿到这个向量存储
)
public interface XiaoxiaAgent {

    @SystemMessage(fromResource = "xiaoxia_prompt.txt")
    Flux<String> chat(@MemoryId int memoryId, @V("userMessage")@UserMessage String userMessage);

}
