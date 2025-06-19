package com.example.Decouverte_Spring_boot.bll.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDetails register(String email, String password, String... roles);
}
