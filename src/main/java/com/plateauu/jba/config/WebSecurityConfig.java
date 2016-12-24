package com.plateauu.jba.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private DataSource dataSource;




    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                    .jdbcAuthentication()
                        .dataSource(dataSource)
                        .withDefaultSchema()
                        .passwordEncoder(encoder())
                        .authoritiesByUsernameQuery("select app_user.name, role.name from app_user " +
                                "join app_user_role on app_user.id = app_user_role.users_id " +
                                "join role on app_user_role.roles_id = role.id " +
                                "where app_user.name = ?")
                        .usersByUsernameQuery("select name, password, enabled from app_user where name = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);


        http
                .addFilterBefore(filter, CsrfFilter.class)
                .authorizeRequests()
                    .antMatchers("/users/**").hasRole("ADMIN")
                    .antMatchers("/users**").hasRole("ADMIN")
                    .antMatchers("/account**").hasRole("USER")
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .loginPage("/login.html")
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/logout.html")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .and()
                .headers()
                    .xssProtection().block(false);





    }
}
