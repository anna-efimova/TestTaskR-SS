package com.example.portfolio.dto.interest;

import com.example.portfolio.model.InterestEntity;

public class InterestDTO {
    private final Long id;
    private final String name;

    public InterestDTO(InterestEntity interestEntity) {
        this.id = interestEntity.getId();
        this.name = interestEntity.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
