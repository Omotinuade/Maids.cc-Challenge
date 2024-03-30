package com.salesmgt.salesmgtsystem.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmgt.salesmgtsystem.dtos.requests.LoginRequest;
import com.salesmgt.salesmgtsystem.security.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static com.salesmgt.salesmgtsystem.utilities.AppUtils.ACCESS_TOKEN_FIELD_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RequiredArgsConstructor
@Slf4j
public class AppAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper mapper = new ObjectMapper();

    private final JwtService jwtService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try (InputStream stream = request.getInputStream()){
            LoginRequest loginRequest = mapper.readValue(stream, LoginRequest.class);
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();


            Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authenticationResult = authenticationManager.authenticate(authentication);
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authenticationResult);
            return authenticationResult;
        }catch (IOException exception){
            throw new BadCredentialsException("Authentication failed");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String token = jwtService.generateTokenFor(authResult.getPrincipal().toString());
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().write(mapper.writeValueAsBytes(Map.of(ACCESS_TOKEN_FIELD_VALUE, token)));
        response.flushBuffer();
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().flush();
    }
}
