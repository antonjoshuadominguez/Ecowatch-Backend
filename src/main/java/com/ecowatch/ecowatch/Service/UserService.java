package com.ecowatch.ecowatch.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ecowatch.ecowatch.Models.User.UserEntity;
import com.ecowatch.ecowatch.Models.User.UserRepository;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepo;


    public String register() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserEntity user = new UserEntity("Adrian", "De Leon", "adrianajdeleon@gmail.com", encoder.encode("12356").toString());
        userRepo.save(user);
        return "Register successful";
    }
}
