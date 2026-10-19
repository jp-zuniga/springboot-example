package org.uam.springbootexample.infrastructure.controllers;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import org.uam.springbootexample.infrastructure.dto.LoginRequest;
import org.uam.springbootexample.infrastructure.dto.LoginResponse;
import org.uam.springbootexample.infrastructure.dto.PerfilResponse;
import org.uam.springbootexample.infrastructure.security.TokenService;

@RestController @RequestMapping("/api/auth") public class AuthController {
    private final AuthenticationManager manager;
    private final TokenService tokens;

    public AuthController(AuthenticationManager manager, TokenService tokens) {
        this.manager = manager;
        this.tokens = tokens;
    }

    @PostMapping("/login") public ResponseEntity<LoginResponse> login(
        @Valid @RequestBody LoginRequest datos
    ) {
        Authentication auth =
            manager.authenticate(new UsernamePasswordAuthenticationToken(
                datos.username(),
                datos.password()
            ));

        return ResponseEntity.ok(new LoginResponse(tokens.crearToken(auth), "Bearer"));
    }

    @GetMapping("/perfil")
    public ResponseEntity<PerfilResponse> perfil(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(new PerfilResponse(
            jwt.getSubject(),
            jwt.getClaimAsStringList("roles")
        ));
    }
}
