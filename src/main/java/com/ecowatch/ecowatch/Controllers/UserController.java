package com.ecowatch.ecowatch.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecowatch.ecowatch.Models.Dto.RegisterDto;
import com.ecowatch.ecowatch.Models.User.UserEntity;
import com.ecowatch.ecowatch.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Register a new user")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserEntity.class)))
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto newUser) {
        return userService.register(newUser);
    }
}
