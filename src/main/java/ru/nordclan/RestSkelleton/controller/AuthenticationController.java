package ru.nordclan.RestSkelleton.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nordclan.RestSkelleton.dto.AuthenticationRequestDTO;
import ru.nordclan.RestSkelleton.entity.User;
import ru.nordclan.RestSkelleton.repository.UserRepository;
import ru.nordclan.RestSkelleton.security.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO requestDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getEmail(), requestDTO.getPassword()));
            User user = userRepository.findByEmail(requestDTO.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User does not exists"));
            String token = jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", requestDTO.getEmail());
            response.put("token", token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }
}
