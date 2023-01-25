package com.example.web3study.security.conf;

import com.example.web3study.security.handler.*;
import com.example.web3study.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Devil
 * @date 2020/1/3 20:53
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {




    @Autowired
    private CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomizeAuthenticationFailureHandler customizeAuthenticationFailureHandler;

    @Autowired
    private CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;


    @Autowired
    private CustomizeAccessDeniedHandler customizeAccessDeniedHandler;

    @Autowired
    private CustomizeAuthenticationEntryPoint customizeAuthenticationEntryPoint;

    @Autowired
    private AdminService  adminService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 认证用户的来源
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 设置为数据库认证并指定是什么认证
         */
        auth.userDetailsService(adminService).passwordEncoder(passwordEncoder);

    }

    /**
     * 配置springsecuryy的相关信息
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin().usernameParameter("user_name").passwordParameter("pass_word")
                .permitAll()
                //成功的与失败的处理器
                .successHandler(authenticationSuccessHandler)
                .failureHandler(customizeAuthenticationFailureHandler)
                .and().logout()
                .permitAll()
                //退出成功
                .logoutSuccessHandler(customizeLogoutSuccessHandler)
                .and().exceptionHandling().
                //权限拒绝处理逻辑
                        accessDeniedHandler(customizeAccessDeniedHandler).
                //匿名用户访问无权限资源时的异常处理
                        authenticationEntryPoint(customizeAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
}