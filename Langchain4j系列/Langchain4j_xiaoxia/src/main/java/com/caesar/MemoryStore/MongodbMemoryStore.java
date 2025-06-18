package com.caesar.MemoryStore;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/17
 */


import com.caesar.domain.ChatInformation;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 我们分析得知：我们只需要实现一下 ChatMemoryStore这个接口就好了
 */
@Component
public class MongodbMemoryStore implements ChatMemoryStore {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {

        // 从一个固定的memoryid中 获取到其指定的 ChatMessage消息
        // 这就是为什么你需要再消息中 存储一下 MemoryId的原因，因为我们是需要使用这个Memoryid 到Mongodb中获取数据的
        Criteria memoryId1 = Criteria.where("memoryId").is(memoryId);
        Query query = new Query(memoryId1);

        // 搜索一下 content的所有内容 从中提取到content
        // 需要将我们获取到的entity转换一下
        ChatInformation chatInformation = mongoTemplate.findOne(query, ChatInformation.class);

        // 做一下空的判断
        if(chatInformation == null)
        {
            return Collections.emptyList();
        }

        return ChatMessageDeserializer.messagesFromJson(chatInformation.getContent()); // 其自己自带了转化的工具


    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {


        // 从一个固定的memoryid中 获取到其指定的 ChatMessage消息
        // 这就是为什么你需要再消息中 存储一下 MemoryId的原因，因为我们是需要使用这个Memoryid 到Mongodb中获取数据的
        Criteria memoryId1 = Criteria.where("memoryId").is(memoryId);
        Query query = new Query(memoryId1);

        // 这里采用upsert直接写过去 就只有一次，这里表示的是修改的部分
        Update update = new Update();
        update.set("content", ChatMessageSerializer.messagesToJson(messages));

        // update是修改的部分，query是搜寻的部分，而其中的ChatMessages提供了表的位置 以及entity的结构
        mongoTemplate.upsert(query, update, ChatInformation.class);

    }

    @Override
    public void deleteMessages(Object memoryId) {

        Criteria memoryId1 = Criteria.where("memoryId").is(memoryId);
        Query query = new Query(memoryId1);

        mongoTemplate.remove(query, ChatInformation.class);

    }
}
