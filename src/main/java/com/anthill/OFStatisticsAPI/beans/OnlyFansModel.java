package com.anthill.OFStatisticsAPI.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@Entity
@JsonIgnoreProperties({"user", "statistics"})
public class OnlyFansModel extends AbstractEntity {

    @OneToMany(mappedBy = "model", cascade = CascadeType.REMOVE)
    private List<ModelStatistic> statistics;

    @JsonProperty("Login")
    private String login;

    @JsonProperty("Password")
    private String password;
}
