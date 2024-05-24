package com.example.portfolio.repository;

import com.example.portfolio.model.InterestEntity;
import com.example.portfolio.model.JobEntity;
import com.example.portfolio.model.SkillEntity;
import com.example.portfolio.model.UserEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InitialDataService {
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final InterestRepository interestRepository;
    private final JobsRepository jobsRepository;

    public InitialDataService(
        UserRepository userRepository,
        SkillRepository skillRepository,
        InterestRepository interestRepository,
        JobsRepository jobsRepository
    ) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.interestRepository = interestRepository;
        this.jobsRepository = jobsRepository;
    }

    @PostConstruct
    public void init() {
        UserEntity user1 = new UserEntity();
        user1.setName("User1");
        user1.setEmail("user1@gmail.com");
        user1.setBio("22 years old");
        userRepository.save(user1);

        SkillEntity javaSkill = new SkillEntity();
        javaSkill.setLevel("junior+");
        javaSkill.setName("java");
        javaSkill.setUser(user1);

        SkillEntity sqlSkill = new SkillEntity();
        sqlSkill.setLevel("junior");
        sqlSkill.setName("sql");
        sqlSkill.setUser(user1);

        skillRepository.saveAll(List.of(javaSkill, sqlSkill));

        InterestEntity interest1 = new InterestEntity();
        interest1.setName("Reading boooks");
        interest1.setUser(user1);
        InterestEntity interest2 = new InterestEntity();
        interest2.setName("Programming");
        interest2.setUser(user1);
        interestRepository.saveAll(List.of(interest1, interest2));

        JobEntity job1 = new JobEntity();
        job1.setCompanyName("Startup");
        job1.setStartWorkingTime(LocalDate.now().minusMonths(1));
        job1.setUser(user1);
        jobsRepository.save(job1);


        UserEntity user2 = new UserEntity();
        user2.setName("User2");
        user2.setEmail("user2@gmail.com");
        user2.setBio("25 years old");
        userRepository.save(user2);

        System.out.println();
    }
}
