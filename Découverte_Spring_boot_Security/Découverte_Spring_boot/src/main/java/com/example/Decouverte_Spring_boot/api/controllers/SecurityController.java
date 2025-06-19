package com.example.Decouverte_Spring_boot.api.controllers;

import com.example.Decouverte_Spring_boot.api.exceptions.HttpException;
import com.example.Decouverte_Spring_boot.api.models.security.SignInForm;
import com.example.Decouverte_Spring_boot.api.models.security.UserDTO;
import com.example.Decouverte_Spring_boot.api.models.security.UserTokenDTO;
import com.example.Decouverte_Spring_boot.api.utils.BindingResultUtil;
import com.example.Decouverte_Spring_boot.config.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final UserDetailsService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/api/login")
    public ResponseEntity<UserTokenDTO> loginAction(
            @Validated @RequestBody SignInForm form,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new HttpException(HttpStatus.PRECONDITION_FAILED, "Form invalid", BindingResultUtil.getErrorsFormBindingResult(bindingResult));
        }

        try {
            UserDetails user = this.userService.loadUserByUsername(form.email());
            if (!this.passwordEncoder.matches(form.password(), user.getPassword())) {
                throw new HttpException(HttpStatus.UNAUTHORIZED, "User or password is incorrect");
            }
            String accessToken = this.jwtUtil.generateToken(user);

            return ResponseEntity.accepted().body(new UserTokenDTO(accessToken, UserDTO.fromDetails(user)));
        } catch (UsernameNotFoundException e) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, "User or password is incorrect");
        }
    }

    @PostMapping("/api/register")
    public ResponseEntity<UserDetails> registerAction() {
        return ResponseEntity.accepted().body(null);
    }
}
