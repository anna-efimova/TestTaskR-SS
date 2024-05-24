package com.example.portfolio;

import com.example.portfolio.dto.user.CreateUserDTO;
import com.example.portfolio.dto.user.UserDTO;
import com.example.portfolio.service.UserService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTests {
    @Autowired
    private UserService userService;

    @Test
    public void createUserPositive() {
        CreateUserDTO createDto = new CreateUserDTO();
        createDto.setName("createUserPositive");
        createDto.setEmail("createUserPositive@gmail.com");
        createDto.setBio("created in createUserPositive");
        UserDTO created = userService.createUser(createDto);
        assertNotNull(created.getId());
        assertEquals(createDto.getName(), created.getName());
        assertEquals(createDto.getEmail(), created.getEmail());
        assertEquals(createDto.getBio(), created.getBio());
    }

    @Test
    public void crateUserWithoutEmail() {
        CreateUserDTO createDto = new CreateUserDTO();
        createDto.setName("crateUserWithoutEmail");
        createDto.setBio("crateUserWithoutEmail");
        assertThrows(ConstraintViolationException.class, () -> userService.createUser(createDto));
    }

    @Test
    public void createUserWithEmptyEmail() {
        CreateUserDTO createDto = new CreateUserDTO();
        createDto.setName("createUserWithEmptyEmail");
        createDto.setEmail("");
        createDto.setBio("created in createUserWithEmptyEmail");
        assertThrows(ConstraintViolationException.class, () -> userService.createUser(createDto));
    }

    @Test
    public void createUserWithoutName() {
        CreateUserDTO createDto = new CreateUserDTO();
        createDto.setEmail("createUserWithoutName@gmail.com");
        createDto.setBio("createUserWithoutName");
        assertThrows(ConstraintViolationException.class, () -> userService.createUser(createDto));
    }

    @Test
    public void testFilterUsers() {
        int totalTestUsers = 5;
        for (int i = 0; i < 5; i++) {
            CreateUserDTO createDto = new CreateUserDTO();
            String name = "testFilterUsers" + i;
            createDto.setName(name);
            createDto.setEmail(name + "@gmail.com");
            createDto.setBio("created in testFilterUsers");
            userService.createUser(createDto);
        }
        Page<UserDTO> allTestUsersByName = userService.getAllUsers("stFilterUse", null,
            PageRequest.of(0, totalTestUsers));

        assertEquals(totalTestUsers, allTestUsersByName.getTotalElements());
        for (UserDTO userDTO : allTestUsersByName) {
            assertTrue(userDTO.getName().contains("stFilterUse"));
        }

        Page<UserDTO> allTestUsersByEmail = userService.getAllUsers(null, "stFilterUse",
            PageRequest.of(0, totalTestUsers));
        assertEquals(totalTestUsers, allTestUsersByEmail.getTotalElements());
        for (UserDTO userDTO : allTestUsersByEmail) {
            assertTrue(userDTO.getEmail().contains("stFilterUse"));
        }


        Page<UserDTO> only1UserByName = userService.getAllUsers("ilterUsers1", null,
            PageRequest.of(0, totalTestUsers));
        assertEquals(1, only1UserByName.getTotalElements());
        for (UserDTO userDTO : only1UserByName) {
            assertTrue(userDTO.getEmail().contains("ilterUsers1"));
        }

        Page<UserDTO> only1UserByEmail = userService.getAllUsers(null, "terUsers1",
            PageRequest.of(0, totalTestUsers));
        assertEquals(1, only1UserByEmail.getTotalElements());
        for (UserDTO userDTO : only1UserByEmail) {
            assertTrue(userDTO.getEmail().contains("terUsers1"));
        }

        Page<UserDTO> usersByNameAndEmail = userService.getAllUsers("erUsers1", "erUsers2",
            PageRequest.of(0, totalTestUsers));
        assertEquals(2, usersByNameAndEmail.getTotalElements());
        for (UserDTO userDTO : usersByNameAndEmail) {
            assertTrue(userDTO.getName().contains("erUsers1") || userDTO.getEmail().contains("erUsers2"));
        }
    }
}
