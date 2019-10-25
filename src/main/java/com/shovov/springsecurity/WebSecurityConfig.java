package com.shovov.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.shovov.springsecurity")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/user").permitAll()
                .antMatchers(HttpMethod.GET,"/languages/**").permitAll()
                .antMatchers(HttpMethod.POST, "/languages/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/ooh").hasAnyRole("USER")
                .antMatchers("/hoo").hasAnyRole("ADMIN")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .deleteCookies()
                .invalidateHttpSession(true)
                .permitAll();
    }

    @Autowired
    @Qualifier("myUserDetailsService")
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        /*auth.inMemoryAuthentication()
                .withUser("fish").password("pass").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");*/
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
