package com.springboot.project.onlineShop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    //Important: Allows the resources files to be found
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/bootstrap/**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/js/**");

    }
    protected void configure(HttpSecurity http) throws Exception {
        //Important: formLogin().loginPage("/login") provides the POST method for the
        //Login page
        http
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .and()
                .authorizeRequests()
                .antMatchers("/cart/**").hasAuthority("ROLE_USER")
                .antMatchers("/get*/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/admin*/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutUrl("/logout");
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Important:Prior to Spring Security 5.0 the default PasswordEncoder was NoOpPasswordEncoder which required plain text passwords.
        // In Spring Security 5, the default is DelegatingPasswordEncoder, which required Password Storage Format.
        //{noop} provides a password storage format
        auth
                .inMemoryAuthentication()
                .withUser("shangtingli@outlook.com").password("{noop}123456").authorities("ROLE_ADMIN");

        auth
                .inMemoryAuthentication()
                .withUser("gracedong@sap.com").password("{noop}beauty").authorities("ROLE_USER");
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email_id, password, enabled FROM users WHERE email_id=? ")
                .authoritiesByUsernameQuery("SELECT email_id,authorities FROM authorities WHERE email_id =?");
    }

}