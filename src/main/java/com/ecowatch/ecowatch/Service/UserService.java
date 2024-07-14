package com.ecowatch.ecowatch.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ecowatch.ecowatch.Models.Dto.RegisterDto;
import com.ecowatch.ecowatch.Models.User.UserEntity;
import com.ecowatch.ecowatch.Models.User.UserRepo;

@Service
public class UserService {
    @Autowired
    public UserRepo userRepo;


    public ResponseEntity<UserEntity> register(RegisterDto newUser) {
        UserEntity newUserEntity = new UserEntity(newUser.getFirstname(), newUser.getLastname(), newUser.getEmail(), newUser.getPassword());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        newUserEntity.setPassword(encoder.encode(newUserEntity.getPassword()));
        if (userRepo.findByEmail(newUserEntity.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
        userRepo.save(newUserEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUserEntity);
    }

    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

}
