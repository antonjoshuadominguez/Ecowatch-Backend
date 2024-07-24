package com.ecowatch.ecowatch.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ecowatch.ecowatch.Models.Dto.RegisterUserDto;
import com.ecowatch.ecowatch.Models.Dto.UpdateUserDto;
import com.ecowatch.ecowatch.Models.User.UserEntity;
import com.ecowatch.ecowatch.Models.User.UserRepo;
import com.ecowatch.ecowatch.config.Generator;

// import lombok.RequiredArgsConstructor;

@Service
public class UserService {
    @Autowired
    public UserRepo userRepo;
    @Autowired
    public EmailService emailService;
    private String otp;

    public ResponseEntity<?> register(RegisterUserDto newUser) {
        UserEntity newUserEntity = new UserEntity(newUser.getFirstname(), newUser.getLastname(), newUser.getEmail(), newUser.getPassword());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        newUserEntity.setPassword(encoder.encode(newUserEntity.getPassword()));
        if (userRepo.findByEmail(newUserEntity.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid Email. This email is already taken.");
        }
        userRepo.save(newUserEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUserEntity);
    }

    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    public String generateOTP() {
        return Generator.generate();
    }

    public ResponseEntity<String> forgetPassword(String email) {
        this.otp = generateOTP();
        UserEntity user = userRepo.findByEmail(email);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid email. No account is associated with this email");
        }
        emailService.sendSimpleMail(email, "Forget Password OTP", "Please use this OTP to reset password in EcoWatch: " + otp);
        return ResponseEntity.ok("OTP successfully sent. Please check your email.");
    }

    public ResponseEntity<String> resetPassword(String email, String otp, String newPassword) {
        if(this.otp == null || !this.otp.equals(otp)) {
            return ResponseEntity.status(HttpStatus.GONE).body("Invalid OTP. Please send another request to forget password");
        }
        UserEntity user = userRepo.findByEmail(email);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid email. No account is associated with this email");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(newPassword));
        userRepo.save(user);
        return ResponseEntity.ok("Successfully changed password.");
    }

    public ResponseEntity<?> updateUser(UpdateUserDto updatedInfo) {
        if(!userRepo.existsById(updatedInfo.getUserId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid user ID. No account is associated with this user ID");
        }
        UserEntity user = userRepo.findById(updatedInfo.getUserId()).get();
        if(!updatedInfo.getEmail().equals(user.getEmail())) {
            if(userRepo.findByEmail(updatedInfo.getEmail())!=null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid email. Email is already taken.");
            }
        }
        user.setEmail(updatedInfo.getEmail());
        user.setFirstname(updatedInfo.getFirstname());
        user.setLastname(updatedInfo.getLastname());
        userRepo.save(user);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<String> changePassword(String email, String oldPassword, String newPassword) {
        UserEntity user = userRepo.findByEmail(email);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid email. No account is associated with this email");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(newPassword, oldPassword)) {
            user.setPassword(newPassword);
        }
        userRepo.save(user);
        return ResponseEntity.ok("Successfully changed password");
    }

    
}
