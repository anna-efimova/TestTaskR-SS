package com.example.portfolio;

import com.example.portfolio.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PortfolioApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(userService);
    }

}
