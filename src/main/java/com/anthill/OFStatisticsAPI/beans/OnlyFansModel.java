package com.anthill.OFStatisticsAPI.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@Getter @Setter
@Entity
public class OnlyFansModel extends AbstractEntity {

    @JsonProperty("Login")
    private String login;

    @JsonProperty("Password")
    private String password;
}
