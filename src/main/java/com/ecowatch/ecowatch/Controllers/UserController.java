package com.ecowatch.ecowatch.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecowatch.ecowatch.Models.Dto.ChangePasswordDto;
import com.ecowatch.ecowatch.Models.Dto.LoginDto;
import com.ecowatch.ecowatch.Models.Dto.RegisterUserDto;
import com.ecowatch.ecowatch.Models.Dto.ResetPasswordDto;
import com.ecowatch.ecowatch.Models.Dto.UpdateUserDto;
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

    @Operation(summary = "Request to forget password")
    @PostMapping("/forget-password")
    public ResponseEntity<String> forgetPassword(@RequestBody String email) {
        return userService.forgetPassword(email);
    }

    @Operation(summary = "Reset password with OTP")
    @PatchMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto reset) {
        return userService.resetPassword(reset.getEmail(), reset.getOtp(), reset.getNewPassword());
    }

    @Operation(summary = "Update user information")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserEntity.class)))
    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDto updatedInfo) {
        return userService.updateUser(updatedInfo);
    }

    @Operation(summary = "Change user password")
    @PatchMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto changePass) {
        return userService.changePassword(changePass.getEmail(), changePass.getOldPassword(), changePass.getNewPassword());
    }

    @Operation(summary = "Register a new user")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserEntity.class)))
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDto newUser) {
        return userService.register(newUser);
    }

    @Operation(summary = "Login")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserEntity.class)))
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto login) {
        return userService.login(login);
    }

    @Operation(summary = "Get all users")
    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Get a user by user ID")
    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getUser(long userId) {
        return userService.getUserById(userId);
    }

    @Operation(summary = "Delete a user permanently")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@RequestParam(required = true) long userId) {
        return userService.deleteUser(userId);
    }
}
