package com.ecowatch.ecowatch.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecowatch.ecowatch.Models.Dto.RegisterDto;
import com.ecowatch.ecowatch.Models.User.UserEntity;
import com.ecowatch.ecowatch.Service.UserService;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/user/all")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/api/user")
    public ResponseEntity<UserEntity> registerUser(RegisterDto newUser) {
        return userService.register(newUser);
    }
}
