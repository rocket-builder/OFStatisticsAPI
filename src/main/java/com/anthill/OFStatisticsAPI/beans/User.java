package com.anthill.OFStatisticsAPI.beans;


import com.anthill.OFStatisticsAPI.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@Getter @Setter
@Entity
public class User extends AbstractEntity {

    @JsonProperty("Login")
    private String login;

    @JsonProperty("Password")
    private String password;

    @JsonProperty("Role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.WORKER;
}
