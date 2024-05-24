package com.example.portfolio.dto.skill;

import com.example.portfolio.model.SkillEntity;

public class SkillDTO {
    private final Long id;
    private final String name;
    private final String level;

    public SkillDTO(SkillEntity skillEntity) {
        this.id = skillEntity.getId();
        this.name = skillEntity.getName();
        this.level = skillEntity.getLevel();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }
}
