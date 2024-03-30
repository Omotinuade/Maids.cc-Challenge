package com.salesmgt.salesmgtsystem.security;


import com.salesmgt.salesmgtsystem.enums.Role;
import com.salesmgt.salesmgtsystem.security.filters.AppAuthenticationFilter;
import com.salesmgt.salesmgtsystem.security.filters.JwtAuthorizationFilter;
import com.salesmgt.salesmgtsystem.security.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.hibernate.cfg.JdbcSettings.USER;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        var authenticationFilter = new AppAuthenticationFilter(authenticationManager, jwtService);
        authenticationFilter.setFilterProcessesUrl("/api/v1/login");
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthorizationFilter(jwtService), authenticationFilter.getClass())
                .authorizeHttpRequests(c->c.requestMatchers(POST,"/api/v1/login", "/api/v1/user")
                        .permitAll()
                        .requestMatchers("/api/v1/sales", "/api/v1/product", "/api/v1/client").hasAnyAuthority(Role.USER.name())
                        .requestMatchers(GET, "/api/v1/report*").hasAnyAuthority(Role.USER.name())
                        .anyRequest().authenticated()).build();
    }




}

