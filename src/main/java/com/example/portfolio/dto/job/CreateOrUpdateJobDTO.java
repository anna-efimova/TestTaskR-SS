package com.example.portfolio.dto.job;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class CreateOrUpdateJobDTO {
    private String companyName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startWorkingTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endWorkingTime;

    public String getCompanyName() {
        return companyName;
    }

    public LocalDate getEndWorkingTime() {
        return endWorkingTime;
    }

    public LocalDate getStartWorkingTime() {
        return startWorkingTime;
    }
}
