package com.aaa.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * SecurityConfig
 * Description:  Spring Security配置
 * Author:Michael
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;



    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**/*.html", "/**/*.css", "/**/*.js","/**/*.png").permitAll()  // 允许访问资源
                .antMatchers("/", "/home", "/about","/special").permitAll() //允许访问这三个路由
                .antMatchers("/admin/**").hasAnyRole("ADMIN")   // 满足该条件下的路由需要ROLE_ADMIN的角色
                .antMatchers("/user/**").hasAnyRole("USER")     // 满足该条件下的路由需要ROLE_USER的角色
                .antMatchers("/special/**").hasAnyRole("USER")   // 满足该条件下的路由需要ROLE_USER的角色
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);           //自定义异常处理

    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/editormd/**","/write/**");
    }

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("user").password("123456").roles("USER");

        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()) .withUser("admin").password("admin").roles("ADMIN");
    }
}
