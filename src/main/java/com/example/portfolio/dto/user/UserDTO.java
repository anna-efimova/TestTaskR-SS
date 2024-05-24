package com.example.portfolio.dto.user;

import com.example.portfolio.model.UserEntity;

public class UserDTO {
    private final Long id;
    private final String name;
    private final String email;
    private final String bio;

    public UserDTO(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
        this.bio = userEntity.getBio();
    }

    public String getBio() {
        return bio;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
