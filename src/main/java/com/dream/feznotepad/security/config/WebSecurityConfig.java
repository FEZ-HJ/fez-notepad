package com.dream.feznotepad.security.config;

import com.dream.feznotepad.security.weChat.WeChatAuthenticationTokenFilter;
import com.dream.feznotepad.security.web.WebUserDetailsService;
import com.dream.feznotepad.security.wx.WeChatAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Created by H.J
 * 2018/12/3
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    WebUserDetailsService webUserDetailsService;

    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    WeChatAuthenticationTokenFilter weChatAuthenticationTokenFilter;

    @Autowired
    private DataSource dataSource;

    @Autowired
    WeChatAuthenticationSecurityConfig weChatAuthenticationSecurityConfig;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        是否建表，第一次建表需要设置为true
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/reg","/login","/authentication/form","/wxLogin").permitAll()
                .anyRequest().authenticated()
                .and()
                    .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(3600)
                    .userDetailsService(webUserDetailsService)
                .and()
                    .formLogin()
                    //跳转到验证登录验证页面
                    .loginPage("/verifyAndRedirect")
                    //登录表单的提交
                    .loginProcessingUrl("/authentication/form")
                    //登录成功后的处理
                    .successHandler(authenticationSuccessHandler)
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .deleteCookies()
                    .permitAll()
        .and().headers().frameOptions().sameOrigin()
        .and().csrf().disable()
        ;

//       添加微信登录配置
//        http.apply(weChatAuthenticationSecurityConfig);
        http.addFilterBefore(weChatAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);
    }

//    设置登录表单的用户名密码的id
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("username").password("password").roles("USER");
    }

    @Override
    public void configure(WebSecurity webSecurity){
        //解决静态资源拦截问题
        webSecurity.ignoring().antMatchers("/start/**","/src/**");
    }

    //添加自定义登录校验
    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(webUserDetailsService);
    }
}
