package com.webapp.framework.config;

import com.webapp.framework.security.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 注解开启了方法级别的保护
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * token认证过滤器
     */
    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;

    // 配置如何验证身份
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 启用基于 HttpServletRequest 的访问限制，开始配置哪些URL需要被保护、哪些不需要被保护
                .authorizeRequests()
                // 允许未登陆用户访问的资源
                .antMatchers( "/favicon.ico","/css/**","/index","/js/**","/images/**").permitAll()
                // /user/**的这个资源需要有ROLE_USER的这个角色才能访问，不然就会提示拒绝访问
                .antMatchers("/user/login").permitAll()
                // 任何尚未匹配的URL只需要验证用户即可访问
                .anyRequest().authenticated()
                .and()
                // 设置登陆相关的配置
                .formLogin()
                // 登陆界面页面跳转URL
                .loginPage("/login")
                // 登陆失败页面跳转URL
                .failureUrl("/login-error")
                // 登陆成功跳转的页面，可以不设置，默认跳转到需要登陆之前的页面
                .defaultSuccessUrl("/index")
                // permitAll表示不需要验证 登录页面，登录失败页面
                .permitAll()
                .and()
                // 权限拒绝页面跳转URL
                .exceptionHandling().accessDeniedPage("/401");
        // 注销登录成功，重定向到首页
        http.logout().logoutSuccessUrl("/");
        // 添加JWT filter
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
