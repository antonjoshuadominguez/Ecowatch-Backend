package com.ecowatch.ecowatch.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecowatch.ecowatch.Service.UserService;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/user")
    public String registerUser() {
        return userService.register();
    }
}
