package com.example.sonin_spring_warehouse.config;

import com.example.sonin_spring_warehouse.repository.CustomerRepository;
import com.example.sonin_spring_warehouse.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomerRepository customerRepository;

    @Autowired
    public SecurityConfig(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(
                new MyUserDetailsService(customerRepository));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET).hasAnyRole("USER", "ADMIN")
            //.antMatchers(HttpMethod.GET).fullyAuthenticated()
            .anyRequest().hasRole("ADMIN")
            .and()
            .httpBasic()
            .and()
            .csrf().disable();
    }
}
