package com.maker.shop.config;

import com.maker.shop.security.service.ShopUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ShopUserDetailsService userService;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**" , "/logo/**");
    }
    @Override
    protected void configure(HttpSecurity http)throws Exception{

        http.authorizeRequests()
                    .antMatchers("/member/login","/member/signup","/member/user","/mainPr","/display","/product/prList","/product/prDetail","/productImg").permitAll()
                .antMatchers("/").hasRole("USER")
                .anyRequest().authenticated()
        .and()
                .formLogin()
                .loginPage("/member/login")
                .defaultSuccessUrl("/member/mypage")
                .and()
                .logout()
                .logoutSuccessUrl("/member/login")
                .invalidateHttpSession(true);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
}
