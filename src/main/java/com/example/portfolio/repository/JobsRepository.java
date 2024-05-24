package com.example.portfolio.repository;

import com.example.portfolio.model.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsRepository extends JpaRepository<JobEntity, Long> {
    List<JobEntity> findAllByUserId(Long userId);
}
