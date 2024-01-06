package com.joaovitor.authapi.controller.auth;

import com.joaovitor.authapi.domain.user.User;
import com.joaovitor.authapi.dto.auth.AuthDTO;
import com.joaovitor.authapi.dto.auth.LoginResponseDTO;
import com.joaovitor.authapi.dto.register.RegisterDTO;
import com.joaovitor.authapi.dto.register.RegisterResponseDTO;
import com.joaovitor.authapi.security.TokenService;
import com.joaovitor.authapi.service.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final AuthService authService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO authDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.username(), authDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterDTO registerDTO) {
        if(this.authService.loadUserByUsername(registerDTO.username()) != null)
            return ResponseEntity.badRequest().build();

        User newUser = new User(registerDTO.username(), registerDTO.password(), registerDTO.role());
        this.authService.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponseDTO(registerDTO.username(), registerDTO.role()));
    }
}
