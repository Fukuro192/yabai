package ma.fukuro192.yabai.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.fukuro192.yabai.entity.User;
import ma.fukuro192.yabai.repository.UserRepository;
import ma.fukuro192.yabai.security.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    public AuthController(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager,
        JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
        @RequestParam(name = "username") String username,
        @RequestParam(name = "email") String email,
        @RequestParam(name = "password") String password
    ) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        User created = userRepository.save(user);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
        @RequestParam(name = "username", required = false) String username,
        @RequestParam(name = "email", required = false) String email,
        @RequestParam(name = "password") String password
    ) {
        if(username == null || username.isEmpty()) return ResponseEntity.badRequest().build();
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );
        return ResponseEntity.ok(jwtTokenProvider.generateToken(authentication));
    }
}
