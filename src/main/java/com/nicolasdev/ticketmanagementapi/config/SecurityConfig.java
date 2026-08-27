package com.nicolasdev.ticketmanagementapi.config;

import com.nicolasdev.ticketmanagementapi.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity)
            throws Exception {

        return httpSecurity

                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))

                .exceptionHandling(exception ->
                        exception
                                .authenticationEntryPoint((
                                        request,
                                        response,
                                        authException) -> {

                                            response.setStatus(
                                                    HttpServletResponse.SC_UNAUTHORIZED);

                                            response.setContentType(
                                                    "application/json");

                                            response.getWriter().write("""
                                                    {
                                                        "error":"Unauthorized",
                                                        "message":"Authentication is required"
                                                    }
                                                    """);

                                        })

                                .accessDeniedHandler((
                                            request,
                                            response,
                                            accessDeniedException) -> {


                                    response.setStatus(
                                            HttpServletResponse.SC_FORBIDDEN);

                                    response.setContentType(
                                            "application/json");

                                    response.getWriter().write("""
                                            {
                                                "error":"Forbidden Request",
                                                "message":"You are not authorized to access this resource"
                                            }
                                            """);
                                }))

                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(
                                        "/auth/**",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**")
                                .permitAll()

                                .anyRequest()
                                .authenticated())

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
