package com.anthill.OFStatisticsAPI.beans;

import com.anthill.OFStatisticsAPI.enums.ExtraStatisticType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@Getter @Setter
@Entity
public class ExtraStatistic extends AbstractEntity {

    private String name = "";

    private int guests, users;

    private ExtraStatisticType type;
}
