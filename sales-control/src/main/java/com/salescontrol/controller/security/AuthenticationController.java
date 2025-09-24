package com.salescontrol.controller.security;

import com.salescontrol.dto.security.LoginDTO;
import com.salescontrol.dto.security.RegisterDTO;
import com.salescontrol.model.security.Users;
import com.salescontrol.repository.security.UsersRepository;
import com.salescontrol.service.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UsersRepository usersRepository;
    private final TokenService tokenService;

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO data) {
        var auth = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication authenticate = authenticationManager.authenticate(auth);
        String token = tokenService.generateToken((Users) authenticate.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDTO data) {
        if (usersRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        String passwordEncode = new BCryptPasswordEncoder().encode(data.password());
        Users users = new Users(data.login(), passwordEncode, data.role());
        usersRepository.save(users);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}