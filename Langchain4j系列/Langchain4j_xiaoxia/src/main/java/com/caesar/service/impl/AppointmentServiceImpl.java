package com.caesar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caesar.domain.Appointment;
import com.caesar.mapper.AppointmentMapper;
import com.caesar.service.AppointmentService;
import org.springframework.stereotype.Service;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/18
 */
@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService {

    /**
     * 功能：查看订单是否存在的
     * （1）下面的行为等价于：WHERE username = ? AND id_card = ? AND department = ? ...
     * （2）这就是 严格的 AND 条件，任意一个不满足，整条记录就查不出来，selectOne() 查不到就返回 null，所以说 这个才会当做是查看这个订单是否存在的
     *
     * 注意的是：有些返回容器的api,有些api是直接返回一个null给你，有些时候是返回一个空的容器 所以很傻逼
     *
     *
     * @param appointment
     * @return
     */
    @Override
    public Appointment getOne(Appointment appointment) {

        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Appointment::getUsername, appointment.getUsername());
        queryWrapper.eq(Appointment::getIdCard, appointment.getIdCard());
        queryWrapper.eq(Appointment::getDepartment, appointment.getDepartment());
        queryWrapper.eq(Appointment::getDate, appointment.getDate());
        queryWrapper.eq(Appointment::getTime, appointment.getTime());

        Appointment appointmentDB = baseMapper.selectOne(queryWrapper); // 根据拼接的条件从db中获取

        return appointmentDB;
    }
}
