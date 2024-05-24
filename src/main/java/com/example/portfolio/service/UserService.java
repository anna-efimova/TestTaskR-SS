package com.example.portfolio.service;

import com.example.portfolio.dto.interest.CreateInterestDTO;
import com.example.portfolio.dto.interest.InterestDTO;
import com.example.portfolio.dto.job.CreateOrUpdateJobDTO;
import com.example.portfolio.dto.job.JobDTO;
import com.example.portfolio.dto.skill.CreateSkillDTO;
import com.example.portfolio.dto.skill.SkillDTO;
import com.example.portfolio.dto.user.CreateUserDTO;
import com.example.portfolio.dto.user.UserDTO;
import com.example.portfolio.exception.ResourceNotFoundException;
import com.example.portfolio.model.InterestEntity;
import com.example.portfolio.model.JobEntity;
import com.example.portfolio.model.SkillEntity;
import com.example.portfolio.model.UserEntity;
import com.example.portfolio.repository.InterestRepository;
import com.example.portfolio.repository.JobsRepository;
import com.example.portfolio.repository.UserRepository;
import com.example.portfolio.repository.SkillRepository;
import com.example.portfolio.repository.filter.UserFilterSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class UserService {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final InterestRepository interestRepository;
    private final JobsRepository jobsRepository;

    @Autowired
    public UserService(
        UserRepository userRepository, SkillRepository skillRepository, InterestRepository interestRepository,
        JobsRepository jobsRepository
    ) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.interestRepository = interestRepository;
        this.jobsRepository = jobsRepository;
    }

    public UserDTO getUserById(Long id) {
        return new UserDTO(findUserOrThrow(id));
    }

    private UserEntity findUserOrThrow(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    public Page<UserDTO> getAllUsers(String nameFilter, String emailFilter, Pageable pageable) {
        Page<UserEntity> users = userRepository.findAll(new UserFilterSpecification(nameFilter, emailFilter), pageable);
        return users.map(UserDTO::new);
    }

    public UserDTO createUser(CreateUserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setBio(userDTO.getBio());
        UserEntity savedUser = userRepository.save(user);
        return new UserDTO(savedUser);
    }


    public List<SkillDTO> getUserSkills(Long userId) {
        List<SkillEntity> userSkills = skillRepository.findAllByUserId(userId);
        return userSkills.stream()
            .map(SkillDTO::new)
            .collect(Collectors.toList());
    }

    public List<InterestDTO> getUserInterests(Long userId) {
        List<InterestEntity> userInterests = interestRepository.findAllByUserId(userId);
        return userInterests.stream()
            .map(InterestDTO::new)
            .collect(Collectors.toList());
    }

    public List<JobDTO> getUserJobs(Long userId) {
        List<JobEntity> userJobs = jobsRepository.findAllByUserId(userId);
        return userJobs.stream()
            .map(JobDTO::new)
            .collect(Collectors.toList());
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void deleteUserSkill(Long skillId) {
        skillRepository.deleteById(skillId);
    }

    public void deleteUserInterest(Long interestId) {
        interestRepository.deleteById(interestId);
    }

    public void deleteUserJob(Long jobId) {
        jobsRepository.deleteById(jobId);
    }

    public InterestDTO addUserInterest(Long userId, CreateInterestDTO interest) {
        UserEntity user = findUserOrThrow(userId);
        InterestEntity interestEntity = new InterestEntity();
        interestEntity.setUser(user);
        interestEntity.setName(interest.getName());
        interestRepository.save(interestEntity);
        return new InterestDTO(interestEntity);
    }

    public SkillDTO addUserSkill(Long userId, CreateSkillDTO skill) {
        UserEntity user = findUserOrThrow(userId);
        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setUser(user);
        skillEntity.setName(skill.getName());
        skillEntity.setLevel(skill.getLevel());
        skillRepository.save(skillEntity);
        return new SkillDTO(skillEntity);
    }

    public JobDTO addUserJob(Long userId, CreateOrUpdateJobDTO job) {
        UserEntity user = findUserOrThrow(userId);
        JobEntity jobEntity = new JobEntity();
        jobEntity.setUser(user);
        jobEntity.setCompanyName(job.getCompanyName());
        jobEntity.setStartWorkingTime(job.getStartWorkingTime());
        jobEntity.setEndWorkingTime(job.getEndWorkingTime());
        jobsRepository.save(jobEntity);
        return new JobDTO(jobEntity);
    }

    public JobDTO updateUserJob(Long jobId, CreateOrUpdateJobDTO job) {
        JobEntity jobEntity = jobsRepository.findById(jobId)
            .orElseThrow(() -> new ResourceNotFoundException("Job not found with id " + jobId));
        jobEntity.setCompanyName(job.getCompanyName());
        jobEntity.setStartWorkingTime(job.getStartWorkingTime());
        jobEntity.setEndWorkingTime(job.getEndWorkingTime());
        jobsRepository.save(jobEntity);
        return new JobDTO(jobEntity);
    }
}
