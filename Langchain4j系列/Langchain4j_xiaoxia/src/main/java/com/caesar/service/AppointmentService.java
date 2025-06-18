package com.caesar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.caesar.domain.Appointment;
import org.springframework.stereotype.Service;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/18
 */


public interface AppointmentService extends IService<Appointment> {

    // 这个是自己定义的数据库方法
    Appointment getOne(Appointment appointment);
}