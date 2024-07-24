package com.ecowatch.ecowatch;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecowatch.ecowatch.Service.UserService;

@SpringBootTest
class EcowatchApplicationTests {

	@Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        assertNotNull(userService, "UserService bean should be loaded");
    }
}
