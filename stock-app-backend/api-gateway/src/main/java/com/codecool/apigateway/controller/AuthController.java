package com.codecool.apigateway.controller;

import com.codecool.apigateway.model.UserCredentials;
import com.codecool.apigateway.security.JwtTokenServices;
import com.codecool.apigateway.service.UserCaller;
import com.codecool.cryptomarketjsonclasses.model.StockAppUser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenServices jwtTokenServices;

    private final UserCaller userCaller;

    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, UserCaller userCaller) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
        this.userCaller = userCaller;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody UserCredentials userCredentials, HttpServletResponse response) {
        try {
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            long userId = userCaller.getUser(userCredentials.getUsername()).getId();
            String token = jwtTokenServices.createToken(userCredentials.getUsername(), roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("username", userCredentials.getUsername());
            model.put("roles", roles);
            model.put("id", userId);
            model.put("token", token); //do we still need this?

            Cookie cookie = new Cookie("token",token);
            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            response.addCookie(cookie);

            response.setHeader("Access-Control-Allow-Credentials","true");
            response.setHeader("Access-Control-Allow-Origin","http://localhost:3000");

            return ResponseEntity.ok(model);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @PostMapping("/registration")
    public void registration(@RequestBody StockAppUser stockAppUser ) {
        stockAppUser.setPassword(passwordEncoder.encode(stockAppUser.getPassword()));
        stockAppUser.setBalance(1000000);
        stockAppUser.setRoles(Arrays.asList("ROLE_USER"));
        userCaller.saveUser(stockAppUser);
    }
}
