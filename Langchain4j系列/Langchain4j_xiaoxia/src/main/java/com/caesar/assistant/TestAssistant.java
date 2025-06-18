package com.caesar.assistant;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/16
 */

/**
 * 注意的是 这里的chatModel的名字是SpringBoot根据配置文件读的配置 自己创建的bean，而这里的chatModel的名字就是bean的
 * 如果这里不确定bean的名字的话，可以自己autowired一下 这个bean，之后点击左边的豆子icon，然后就可以看到autoconfig文件中
 * 定义的@bean构造了，这个时候的方法名字就是bean的名字，写这个名字就可以了
 *
 * 而且需要注意的是 Aiservice里面还包含了一个Service 所以其是被ioc容器管理的，所以是可以随地注入的
 */

@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemory = "chatMemory"
)
public interface TestAssistant {

    String chat(String userMessage);

}
