package com.example.portfolio.controller;

import com.example.portfolio.dto.interest.CreateInterestDTO;
import com.example.portfolio.dto.interest.InterestDTO;
import com.example.portfolio.dto.job.CreateOrUpdateJobDTO;
import com.example.portfolio.dto.job.JobDTO;
import com.example.portfolio.dto.skill.CreateSkillDTO;
import com.example.portfolio.dto.skill.SkillDTO;
import com.example.portfolio.dto.user.CreateUserDTO;
import com.example.portfolio.dto.user.UserDTO;
import com.example.portfolio.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAllUsers(
        @RequestParam(required = false) String nameFilter,
        @RequestParam(required = false) String emailFilter,
        Pageable pageable
    ) {
        return ResponseEntity.ok(userService.getAllUsers(nameFilter, emailFilter, pageable));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/{userId}/skills")
    public ResponseEntity<List<SkillDTO>> getUserSkills(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserSkills(userId));
    }

    @PostMapping("/{userId}/skills")
    public ResponseEntity<SkillDTO> addUserSkill(
        @PathVariable Long userId,
        @RequestBody CreateSkillDTO skill
    ) {
        return ResponseEntity.ok(userService.addUserSkill(userId, skill));
    }


    @DeleteMapping("/skills/{skillId}")
    public void deleteUserSkill(@PathVariable Long userId, @PathVariable Long skillId) {
        userService.deleteUserSkill(skillId);
    }

    @GetMapping("/{userId}/interests")
    public ResponseEntity<List<InterestDTO>> getUserInterests(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserInterests(userId));
    }

    @PostMapping("/{userId}/interests")
    public ResponseEntity<InterestDTO> addUserInterest(
        @PathVariable Long userId,
        @RequestBody CreateInterestDTO interest
    ) {
        return ResponseEntity.ok(userService.addUserInterest(userId, interest));
    }


    @DeleteMapping("/interests/{interestId}")
    public void deleteUserInterest(@PathVariable Long userId, @PathVariable Long interestId) {
        userService.deleteUserInterest(interestId);
    }

    @GetMapping("/{userId}/jobs")
    public ResponseEntity<List<JobDTO>> getUserJobs(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserJobs(userId));
    }

    @PostMapping("/{userId}/jobs")
    public ResponseEntity<JobDTO> addUserJob(
        @PathVariable Long userId,
        @RequestBody CreateOrUpdateJobDTO job
    ) {
        return ResponseEntity.ok(userService.addUserJob(userId, job));
    }

    @PutMapping("/jobs/{jobId}")
    public ResponseEntity<JobDTO> updateUserJob(
        @PathVariable Long jobId,
        @RequestBody CreateOrUpdateJobDTO job
    ) {
        return ResponseEntity.ok(userService.updateUserJob(jobId, job));
    }


    @DeleteMapping("/jobs/{jobId}")
    public void deleteUserJob(@PathVariable Long jobId) {
        userService.deleteUserJob(jobId);
    }
}
