package com.example.portfolio;

import com.example.portfolio.dto.skill.CreateSkillDTO;
import com.example.portfolio.dto.skill.SkillDTO;
import com.example.portfolio.dto.user.CreateUserDTO;
import com.example.portfolio.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SkillTests {
    private Long skillTestUserId = null;
    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp() {
        if (skillTestUserId == null) {
            CreateUserDTO createDto = new CreateUserDTO();
            createDto.setName("skillTestUser");
            createDto.setEmail("skillTestUser@gmail.com");
            createDto.setBio("created in SkillTests");
            skillTestUserId = userService.createUser(createDto).getId();
        }
    }

    @Test
    public void testAddSkill() {
        CreateSkillDTO createSkillDTO = new CreateSkillDTO();
        createSkillDTO.setName("12123112");
        createSkillDTO.setLevel("asdadasd");
        List<SkillDTO> existsSkills = userService.getUserSkills(skillTestUserId);
        for (SkillDTO existsSkill : existsSkills) {
            assertNotEquals(existsSkill.getName(), createSkillDTO.getName());
        }

        userService.addUserSkill(skillTestUserId, createSkillDTO);

        List<SkillDTO> newSkills = userService.getUserSkills(skillTestUserId);

        assertTrue(newSkills.stream().anyMatch(skill -> skill.getName().equals(createSkillDTO.getName())));
    }

    @Test
    public void testDeleteSkill() {
        CreateSkillDTO createSkillDTO = new CreateSkillDTO();
        createSkillDTO.setName("testDeleteSkill");
        createSkillDTO.setLevel("testDeleteSkill");
        SkillDTO deleteSkill = userService.addUserSkill(skillTestUserId, createSkillDTO);
        List<SkillDTO> skills = userService.getUserSkills(skillTestUserId);
        assertTrue(skills.stream().anyMatch(skill -> skill.getId().equals(deleteSkill.getId())));
        userService.deleteUserSkill(deleteSkill.getId());
        List<SkillDTO> newSkills = userService.getUserSkills(skillTestUserId);
        assertTrue(newSkills.stream().noneMatch(skill -> skill.getId().equals(deleteSkill.getId())));
    }


}
