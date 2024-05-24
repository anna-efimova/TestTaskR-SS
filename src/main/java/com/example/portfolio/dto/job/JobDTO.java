package com.example.portfolio.dto.job;

import com.example.portfolio.model.JobEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class JobDTO {
    private final Long id;
    private final String companyName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate startWorkingTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate endWorkingTime;

    public JobDTO(JobEntity jobEntity) {
        this.id = jobEntity.getId();
        this.companyName = jobEntity.getCompanyName();
        this.startWorkingTime = jobEntity.getStartWorkingTime();
        this.endWorkingTime = jobEntity.getEndWorkingTime();
    }

    public String getCompanyName() {
        return companyName;
    }

    public LocalDate getEndWorkingTime() {
        return endWorkingTime;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getStartWorkingTime() {
        return startWorkingTime;
    }
}
