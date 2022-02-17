package com.anthill.OFStatisticsAPI.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter @Setter
@Entity
@JsonIgnoreProperties({"user"})
public class OnlyFansModel extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonProperty("Login")
    private String login;

    @JsonProperty("Password")
    private String password;
}
