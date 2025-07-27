package com.mibrary.management.app.service;

import com.mibrary.management.app.JWT.JwtService;
import com.mibrary.management.app.dto.LoginRequestDTO;
import com.mibrary.management.app.dto.LoginResponseDTO;
import com.mibrary.management.app.dto.RegisterRequestDTO;
import com.mibrary.management.app.entity.User;
import com.mibrary.management.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public User registerNormalUser(RegisterRequestDTO registerRequestDTO) {

        if (userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("User already register");
        }
        Set<String> roles = new HashSet<String>();
        roles.add("ROLE_USER");

        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public User registerAdminUser(RegisterRequestDTO registerRequestDTO) {

        if (userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("User already register");
        }

        Set<String> roles=new HashSet<String>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");

        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);
    }


    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword())
                );
        User user = userRepository.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(()-> new RuntimeException("User not found"));

        String token=jwtService.generateToken(user);

        return LoginResponseDTO.builder()
                .token(token)
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();


    }
}
