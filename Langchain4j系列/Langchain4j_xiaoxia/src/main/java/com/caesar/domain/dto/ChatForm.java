package com.caesar.domain.dto;

import lombok.Data;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/17
 */


/**
 * 这个是用来接受前端请求的。前端的请求 包装成一个类
 * 所以还需要一个controller来处理这个类，将其转化成chatInformation
 */

@Data
public class ChatForm {

    private int memoryId;

    private String message;

}
