package com.example.portfolio.repository;

import com.example.portfolio.model.InterestEntity;
import com.example.portfolio.model.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestRepository extends JpaRepository<InterestEntity, Long> {
    List<InterestEntity> findAllByUserId(Long userId);
}
