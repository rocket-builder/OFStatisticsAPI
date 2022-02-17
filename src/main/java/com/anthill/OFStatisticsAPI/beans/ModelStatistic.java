package com.anthill.OFStatisticsAPI.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter @Setter
@Entity
public class ModelStatistic extends AbstractEntity {

    @ManyToOne
    @JsonProperty("OnlyFansModel")
    private OnlyFansModel model;

    @JsonProperty("Statistic")
    @ManyToOne(cascade = CascadeType.ALL)
    private Statistic statistic;
}
