package com.anthill.OFStatisticsAPI.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@NoArgsConstructor
@Getter @Setter
@Entity
public class ModelStatistic extends AbstractEntity {

    @ManyToOne
    @JsonProperty("onlyFansModel")
    private OnlyFansModel model;

    @ManyToOne
    private Statistic statistic;
}
