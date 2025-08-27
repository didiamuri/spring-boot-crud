package cd.vodacom.springbootcrud.controller;

import cd.vodacom.springbootcrud.entity.User;
import cd.vodacom.springbootcrud.repository.UserRepository;
import cd.vodacom.springbootcrud.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RegistrationLoginController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
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
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            return ResponseUtil.ok("Login Successfully", null);
        } catch (Exception e) {
            return ResponseUtil.unauthorized("Invalid email or password");
        }
    }
}
