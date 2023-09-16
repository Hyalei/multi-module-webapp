package com.webapp.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webapp.system.domain.User;
import com.webapp.system.mapper.UserMapper;
import com.webapp.system.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
