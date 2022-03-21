package com.example.sonin_spring_warehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .passwordEncoder(passwordEncoder())
            .withUser("user")
            .password(passwordEncoder().encode("user"))
            .roles("ADMIN","USER");
        //.password("{noop}password")

        //попробовать передать "{bcrypt}user" в .encode
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().fullyAuthenticated()
            .and()
            .httpBasic()
            .and()
            .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //{noop}  - не шифрует
        //в д.с. стабильно как BCryptPasswordEncoder?

        //как в д.с. default расшифровку добавить, если возвращается PasswordEncoder?
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
