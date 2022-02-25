package com.anthill.OFStatisticsAPI.beans;

import com.anthill.OFStatisticsAPI.enums.ExtraStatisticType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter @Setter
@Entity
@JsonIgnoreProperties({"statistic"})
public class ExtraStatistic extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "statistic_id")
    private Statistic statistic;

    @JsonProperty("Name")
    private String name = "";

    @JsonProperty("Guests")
    private int guests;

    @JsonProperty("Users")
    private int users;

    @JsonProperty("Type")
    private ExtraStatisticType type;
}
