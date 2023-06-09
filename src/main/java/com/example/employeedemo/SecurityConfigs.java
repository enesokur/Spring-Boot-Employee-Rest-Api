package com.example.employeedemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfigs {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(john,mary,susan);
    }
    */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authorize ->
                authorize.
                        antMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
                        .antMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                        .antMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
                        .antMatchers(HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
                        .antMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
                        .anyRequest().authenticated()



        );
        http.csrf().disable();
        http.httpBasic();
        return http.build();
    }

}
