package com.caesar.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
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
        chatMemoryProvider = "chatMemoryProvider",
        tools = {"calculationTools"}
)
public interface SeparateChatAssistant {


    /**
     *
     * @param memoryId 每一个聊天记录的id
     * @param userMessage 这个显式的注明一下 这个是用户输入的userMessage(@userMessage也是告诉你 注明一下 这个是用户的信息)
     * @return
     */
//    @SystemMessage(fromResource = "PromptTest.txt")
    @UserMessage("你是我的好朋友，请用上海话回答问题，并且添加一些表情符号。{{userMessage}}")
    String chat(@MemoryId int memoryId, @V("userMessage") String userMessage);

    // 上述注意 ，你要么使用 @UserMessage String userMessage来注明这个是userMessage
    // 要么在方法上 使用 @UserMessage("你是我的好朋友，请用上海话回答问题，并且添加一些表情符号。{{userMessage}}") 以及在参数上使用@V("userMessage") String userMessage
    // 两个是不能同时使用的
    // @V是用于 将@UserMessage或者是 @SystemMessage中的{{text}}拼接好，一起发给大模型的。而@V拼接是 chat方法中 输入的参数，将参数的值 直接拼接过去
    // @V("text“)中的text一定是和{{text}}名字一样的

}
