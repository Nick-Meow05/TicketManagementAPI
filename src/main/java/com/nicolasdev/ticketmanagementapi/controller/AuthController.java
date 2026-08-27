package com.nicolasdev.ticketmanagementapi.controller;

import com.nicolasdev.ticketmanagementapi.dto.AuthResponseDTO;
import com.nicolasdev.ticketmanagementapi.dto.LoginRequestDTO;
import com.nicolasdev.ticketmanagementapi.dto.RegisterRequestDTO;
import com.nicolasdev.ticketmanagementapi.entity.User;
import com.nicolasdev.ticketmanagementapi.security.JwtService;
import com.nicolasdev.ticketmanagementapi.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "User Account",
        description = "Operations related to User accounts")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestBody RegisterRequestDTO request){

        userService.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(
            @RequestBody LoginRequestDTO request){

        User user = userService.loginUser(request);

        String token = jwtService.generateToken(
                user.getUsername());

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String roles(){

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return authentication
                .getAuthorities()
                .toString();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String TestUser(){

        return "Authentication Successful";
    }


}
