package com.roy.config;

import com.roy.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LoginService loginService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login.ftl", "/static/**", "/loginController/tologin");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.authorizeRequests()

                .antMatchers("/").permitAll()
                .antMatchers("/loginController/**").permitAll();

        http.authorizeRequests()
                .antMatchers("/adminController/**").hasRole("ADMIN")

                .antMatchers("/studentController/**").hasRole("STUDENT")

                .antMatchers("/teacherController/**").hasRole("TEACHER")

                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {

                        return o;
                    }
                })
                .and()
                .formLogin().loginPage("/loginController/tologin").loginProcessingUrl("/loginController/dologin")
                .usernameParameter("account").passwordParameter("password")

                //  自定义登录界面
                .and()
                .formLogin().loginPage("/loginController/tologin").defaultSuccessUrl("/toInitLogin").failureUrl("/loginController/tologin")
                .and()
                .logout().logoutUrl("/loginController/logout").logoutSuccessUrl("/index")
                //处理异常,拒绝访问就重定向到403页面
                .and().exceptionHandling().accessDeniedPage("/403")

                .and()
                .logout().permitAll()
                .and().csrf().disable()
                .exceptionHandling()
                .and()
                .rememberMe()
                .tokenValiditySeconds(604800) //记住我功能，cookies有限期是一周
                .rememberMeParameter("remember-me") //登陆时是否激活记住我功能的参数名字，在登陆页面有展示
                .rememberMeCookieName("workspace"); //cookies的名字，登陆后可以通过浏览器查看cookies名字
    }
}