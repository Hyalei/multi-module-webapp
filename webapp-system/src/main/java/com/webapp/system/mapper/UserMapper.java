package com.webapp.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webapp.system.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
