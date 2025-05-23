package com.desafio.aprendendoSpring.infra.security;

import com.desafio.aprendendoSpring.infra.exception.CustomAuthenticationEntryPoint;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) throws Exception {
        return
                http.csrf(csrf -> csrf.disable())
                        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authorizeHttpRequests(req -> {
                            req.requestMatchers("/login").permitAll();
                            req.requestMatchers("/cadastrar").permitAll();
                            req.requestMatchers("/v3/api-docs/**","/swagger-ui.html","/swagger-ui/**").permitAll();
                            req.requestMatchers(HttpMethod.DELETE,"/pacientes/**").hasRole("ADMIN");
                            req.requestMatchers(HttpMethod.DELETE,"/pacientes/**").hasRole("ADMIN");
                            req.anyRequest().authenticated();
                        })
                        .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(customAuthenticationEntryPoint))
                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)

                        .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
