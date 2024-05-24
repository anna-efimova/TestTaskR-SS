package com.example.portfolio.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Table(name = "jobs")
@Entity
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String companyName;
    @NotNull
    private LocalDate startWorkingTime;
    @Nullable
    private LocalDate endWorkingTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDate getEndWorkingTime() {
        return endWorkingTime;
    }

    public void setEndWorkingTime(LocalDate endWorkingTime) {
        this.endWorkingTime = endWorkingTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartWorkingTime() {
        return startWorkingTime;
    }

    public void setStartWorkingTime(LocalDate startWorkingTime) {
        this.startWorkingTime = startWorkingTime;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
