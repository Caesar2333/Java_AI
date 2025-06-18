package com.caesar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("chatMemory")
public class ChatInformation {

    @Id
    private String id;


    private int memoryId; // 这个是记忆的id，应该是每一个memoryid对应一个聊天记录的


    private String content; // 存储当前聊天记录列表的json字符串


}
