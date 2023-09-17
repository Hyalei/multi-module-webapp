package com.webapp.framework.web.service;


import com.webapp.common.core.domain.entity.SysUser;
import com.webapp.common.core.domain.model.LoginUser;
import com.webapp.common.exception.ServiceException;
import com.webapp.common.utils.SecurityUtils;
import com.webapp.common.utils.StringUtils;
import com.webapp.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        SysUser user = userService.selectUserByUserName(username);

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        LoginUser loginUser = new LoginUser(user.getUserId(), user.getDeptId(), user, null);
        return loginUser;
    }
}
