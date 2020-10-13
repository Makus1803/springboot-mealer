package com.glocki.springbootmealer.web.apis;

import com.glocki.springbootmealer.domain.model.user.*;
import com.glocki.springbootmealer.web.payload.request.RegistrationRequest;
import com.glocki.springbootmealer.web.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
public class RegistrationController {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserDetailsService service;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegistrationController() {
    }

    @PostMapping("/api/registrations")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest registrationRequest){
        if (userRepository.existsByUsername(registrationRequest.getUsername())){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("User with that username exists"));
        }
        if (userRepository.existsByEmail(registrationRequest.getEmail())){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Email address is already in use"));
        }

        User user = User.create(
                registrationRequest.getUsername(),
                passwordEncoder.encode(registrationRequest.getPassword()),
                registrationRequest.getName(),
                registrationRequest.getEmail());

        Set<String> rolesStr = registrationRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (rolesStr == null){
            Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: role not found"));
            roles.add(userRole);
        } else {
            rolesStr.forEach(role -> {
                if ("admin".equals(role)) {
                    Role adminRole = roleRepository.findByName(EnumRole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: role not found"));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: role not found"));
                    roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Registration completed successfully."));
    }
}
