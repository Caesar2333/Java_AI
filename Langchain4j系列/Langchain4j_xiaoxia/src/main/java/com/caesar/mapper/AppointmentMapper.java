package com.caesar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caesar.domain.Appointment;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author: Yuxian Zheng
 * Version: 1.0
 * Date: 2025/6/18
 */
@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {
}
