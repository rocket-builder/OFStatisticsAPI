package com.anthill.OFStatisticsAPI.beans;

import com.anthill.OFStatisticsAPI.enums.ExtraStatisticType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@Getter @Setter
@Entity
public class ExtraStatistic extends AbstractEntity {

    @JsonProperty("Name")
    private String name = "";

    @JsonProperty("Guests")
    private int guests;

    @JsonProperty("Users")
    private int users;

    @JsonProperty("Type")
    private ExtraStatisticType type;
}
