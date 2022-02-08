package com.anthill.OFStatisticsAPI.beans;

import com.anthill.OFStatisticsAPI.enums.Schedule;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Period;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@Entity
public class Statistic extends AbstractEntity {

    @OneToMany
    private List<ExtraStatistic> extraStatistic;

    private Period userTime, guestTime;
    private int userTotal, guestTotal;

    private BigDecimal earning;

    @JsonProperty("countSub")
    private int subCount;

    private Schedule schedule;

    @JsonProperty("date")
    private Date statisticDate;
}
