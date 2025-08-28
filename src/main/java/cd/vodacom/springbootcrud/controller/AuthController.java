package cd.vodacom.springbootcrud.controller;

import cd.vodacom.springbootcrud.config.JwtConfig;
import cd.vodacom.springbootcrud.entity.User;
import cd.vodacom.springbootcrud.repository.UserRepository;
import cd.vodacom.springbootcrud.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            return ResponseUtil.conflict("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User created = userRepository.save(user);
        return ResponseUtil.created("User Created Successfully", created);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                Map<String, Object> data = new HashMap<>();
                data.put("token", jwtConfig.generateJwtToken(user.getEmail()));
                data.put("type", "Bearer");
                return ResponseUtil.ok("Login Successfully", data);
            }
            return ResponseUtil.unauthorized("Invalid email or password");
        } catch (AuthenticationException e) {
            return ResponseUtil.internalError("Internal Server Error");
        }
    }
}
