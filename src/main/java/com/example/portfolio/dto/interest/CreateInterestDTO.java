package com.example.portfolio.dto.interest;

import jakarta.validation.constraints.NotEmpty;

public class CreateInterestDTO {
    @NotEmpty
    private  String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
