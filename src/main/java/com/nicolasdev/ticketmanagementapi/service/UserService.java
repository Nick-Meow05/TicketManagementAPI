package com.nicolasdev.ticketmanagementapi.service;

import com.nicolasdev.ticketmanagementapi.dto.LoginRequestDTO;
import com.nicolasdev.ticketmanagementapi.dto.RegisterRequestDTO;
import com.nicolasdev.ticketmanagementapi.entity.User;
import com.nicolasdev.ticketmanagementapi.exception.InvalidCredentialsException;
import com.nicolasdev.ticketmanagementapi.exception.UserAlreadyExistsException;
import com.nicolasdev.ticketmanagementapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequestDTO request){

        Optional<User> existingUser = userRepository.findByUsername(
                request.getUsername());

        if (existingUser.isPresent()){
            throw new UserAlreadyExistsException("Username already exists");
        }

        User user = new User();

        user.setUsername(request.getUsername());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole("USER");

        userRepository.save(user);
    }

    public User loginUser(LoginRequestDTO request){

        User user = userRepository.findByUsername(
                request.getUsername())
                .orElseThrow(() ->
                        new InvalidCredentialsException(
                                "Invalid username or password"));

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword());

        if(!matches) {

            throw new InvalidCredentialsException("Invalid username or password");
        }

        return user;
    }
}
