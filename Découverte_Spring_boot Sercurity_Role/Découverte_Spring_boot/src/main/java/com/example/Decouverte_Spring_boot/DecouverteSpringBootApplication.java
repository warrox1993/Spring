package com.example.Decouverte_Spring_boot;

import com.example.Decouverte_Spring_boot.dal.entities.security.RoleEntity;
import com.example.Decouverte_Spring_boot.dal.entities.security.UserEntity;
import com.example.Decouverte_Spring_boot.dal.repositories.security.RoleRepository;
import com.example.Decouverte_Spring_boot.dal.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class DecouverteSpringBootApplication {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(DecouverteSpringBootApplication.class, args);

    }


    @Profile("dev")
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        log.info("Application ready");

        List<RoleEntity> authorities = List.of(
                new RoleEntity("ADMIN"),
                new RoleEntity("USER")
        );

        this.roleRepository.saveAll(authorities);

        List<RoleEntity> roles = this.roleRepository.findAll();
        RoleEntity userRole = roles.stream().filter(r -> r.getName().equals("USER")).findFirst().orElseThrow();

        UserEntity admin = new UserEntity();
        admin.setEmail("admin@bstorm.be");
        admin.setPassword(passwordEncoder.encode("Test1234="));
        admin.setAuthorities(roles);

        UserEntity user = new UserEntity();
        user.setEmail("user@bstorm.be");
        user.setPassword(passwordEncoder.encode("Test1234="));
        user.setAuthorities(List.of(userRole));
        this.userRepository.save(admin);
        this.userRepository.save(user);
    }
}