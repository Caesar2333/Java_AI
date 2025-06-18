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
@Document("chatMemory") // 表示的是在哪一个colleciton，相当于mysql中的表格table
public class Messages {

    // 	✅ 要写！加上 @Id，用 String 类型
    //ObjectId 是 MongoDB 独有的吗？✅ 是的，不写 _id 就会自动生成一个 ObjectId
    // 是不是必须用 ObjectId 作为主键？❌ 不是，任何类型都可以，只要唯一如果你自己设置了 user.setId("user-123")，那就用你给的值
    // 这里可以直接写String 而不是ObejectId，以为Mongodb会自动转换的
    @Id
    private String id;

//    private int memoryId; 这里需要注意的是 memoryid 可以选择存储在mongodb中的，下一次的更改就可以用的上了

    private String message; // 对话的内容


}
