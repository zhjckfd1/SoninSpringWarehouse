package com.example.sonin_spring_warehouse.service.impl;

import com.example.sonin_spring_warehouse.model.Customer;
import com.example.sonin_spring_warehouse.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;


    @Autowired
    public MyUserDetailsService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Customer customer = customerRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        return User.withUsername(customer.getLogin())
                   .accountLocked(!customer.isActive())
                   .password(encoder.encode(customer.getPassword()))
                   .roles(customer.getPart().getCode())
                   .build();
    }
}
