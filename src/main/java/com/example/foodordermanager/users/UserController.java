package com.example.foodordermanager.users;

import com.example.foodordermanager.config.JwtService;
import com.example.foodordermanager.users.dto.RegisterUserDTO;
import com.example.foodordermanager.users.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final JwtService jwtService;
    private final UserService userService;

    public UserController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        Role role = userService.login(userDTO.getUsername(), userDTO.getPassword());
        if (role != null) {
            String token = jwtService.generateToken(userService.findByUsername(userDTO.getUsername()));
            return ResponseEntity.ok("Token: " + token + ", Role: " + role.name());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users")
    public ResponseEntity<UserEntity> createUser(@RequestBody RegisterUserDTO userDTO) {
        Role role = Role.valueOf(userDTO.getRole().toUpperCase());
        UserEntity user = userService.createUser(userDTO.getUsername(), userDTO.getPassword(), role);
        return ResponseEntity.ok(user);
    }

}
