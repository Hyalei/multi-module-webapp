package com.webapp.admin.web.controller.system;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.webapp.common.core.controller.BaseController;
import com.webapp.common.core.domain.entity.SysUser;
import com.webapp.common.core.domain.model.LoginUser;
import com.webapp.common.utils.SecurityUtils;
import com.webapp.framework.web.service.TokenService;
import com.webapp.system.domain.User;
import com.webapp.system.mapper.UserMapper;
import com.webapp.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {


    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenService tokenService;


    @GetMapping("/login")
    public String login() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
//        LoginUser loginUser = new LoginUser(new SysUser(), null);
        return tokenService.createToken(loginUser);
    }


    @GetMapping("/list")
    public String list() {
        Page<User> userPage = userService.page(new Page<User>(2, 3), null);
        return JSONUtil.toJsonPrettyStr(userPage);
    }


    @PostMapping("/add")
    public String add(@RequestBody List<User> userList) {
        boolean saveBatch = userService.saveBatch(userList, 100);
        return JSONUtil.toJsonPrettyStr(saveBatch);
    }
}
