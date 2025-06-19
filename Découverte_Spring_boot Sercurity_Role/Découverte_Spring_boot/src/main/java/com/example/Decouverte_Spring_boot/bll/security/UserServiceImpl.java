package com.example.Decouverte_Spring_boot.bll.security;

import com.example.Decouverte_Spring_boot.dal.entities.security.RoleEntity;
import com.example.Decouverte_Spring_boot.dal.entities.security.UserEntity;
import com.example.Decouverte_Spring_boot.dal.repositories.security.RoleRepository;
import com.example.Decouverte_Spring_boot.dal.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserDetails register(String email, String password, String... roles) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        List<RoleEntity> rolesEntities = this.roleRepository.findAllByNames(Arrays.stream(roles).toList());

        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setAuthorities(rolesEntities);

        return userRepository.save(user);
    }
}
