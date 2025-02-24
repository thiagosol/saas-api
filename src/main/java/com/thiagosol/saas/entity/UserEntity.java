package com.thiagosol.saas.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class UserEntity extends PanacheEntity {

    @NotBlank
    public String name;

    @Email
    @NotBlank
    public String email;
}
