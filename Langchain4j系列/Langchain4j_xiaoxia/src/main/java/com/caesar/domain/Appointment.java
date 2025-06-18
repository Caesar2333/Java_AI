package com.caesar.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @TableId(type = IdType.AUTO) // 这个注解是表示主键 并且这个主键是跟随数据库的自增来增加的（使用的前提是数据库的主键是autoincreasement的）
    private Long id;
    private String username;
    private String idCard;
    private String department;
    private String date;
    private String time;
    private String doctorName;





}
