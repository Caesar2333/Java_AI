package com.caesar.config;

import com.caesar.MemoryStore.MongodbMemoryStore;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/17
 */
@Configuration
public class SeparateMemoryChatConfig {

    @Autowired
    MongodbMemoryStore mongodbMemoryStore;



    // 这个ChatMemoryProvider本质是一个接口，其实就是之前的chatMemory套壳，
    // 目的是为了中间插一步，让不同的memoryId，返回不同的chatMemoryStore的，让其每一个都有不同的实例
    // 而如果没有这个provider的话，如果直接默认的 MessageWindowChatMemory.withMaxMessages(10); 你的聊天记录中 都是存在于同一个store当中的
    // 换句话说 分开存储的就是和provider使用的，这样才是有机会使用到 memoryid的
    @Bean
    public ChatMemoryProvider chatMemoryProvider() {

        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(10)
                .chatMemoryStore(mongodbMemoryStore)
                .build();

        // 上述的lambda表达式的本质 是一个对象，
        // 既然我们这个方法 返回的是ChatMemoryProvider这个类型的对象，而这个类型是一个接口，并且又是一个函数式接口，只有一个方法，
        // 要返回这个类型的对象的话，那只能是实现了其中这个方法的对象
        // 而上述的lambada的本质就是一个对象，且也实现其中的方法，如果看不懂的话，也可以写成匿名内部类的形式。
        // 点进 ChatMemoryProvider 可以看到输入的参数  ChatMemory get(Object memoryId); 输入的是memoryid，对象这lambda表达式的左边。
        // 而这个方法的返回值是 ChatMemory，而我们后半部分 MessageWindowChatMemory.builder().build()点进源码看的话，返回的是MessageWindowChatMemory，是ChatMemory的实现类
        // 刚好完成的闭环，而MessageWindowChatMemory有个属性，private final ChatMemoryStore store; 里面有两个实现类
        // InMemoryChatMemoryStore 和SingleSlotChatMemoryStore 两个实现类，正是存储记忆的地方。
        // 如果你在创建MessageWindowChatMemory的时候，没有明确store的话，默认使用的是 SingleSlotChatMemoryStore这个的


        // maxMessages这个东西，限制的不是“你项目存了多少条消息”，而是 大模型在推理时能看到的“上下文窗口”最多包含多少条历史消息。


    }


}
