package com.plateauu.jba.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private DataSource dataSource;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("admin").password("admin").roles("ADMIN").build());
//        return manager;
//    }


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
                        .authoritiesByUsernameQuery("select user.name, role.name from user " +
                                "join user_role on user.id = user_role.users_id " +
                                "join role on user_role.roles_id = role.id " +
                                "where user.name = ?")
                        .usersByUsernameQuery("select name, password, enabled from user where name = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/users/**").hasRole("ADMIN")
                    .antMatchers("/users.html").hasRole("ADMIN")
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .loginPage("/login.html")
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/logout.html")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true);






    }
}
