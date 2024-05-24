package com.example.portfolio.dto.skill;

import com.example.portfolio.model.SkillEntity;

public class CreateSkillDTO {

    private String name;
    private String level;

    public void setLevel(String level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }
}
