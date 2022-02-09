package com.anthill.OFStatisticsAPI.beans;

import com.anthill.OFStatisticsAPI.enums.Schedule;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
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

    @JsonProperty("ExtraStatistics")
    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<ExtraStatistic> extraStatistic;

    @JsonProperty("UserTime")
    @JsonFormat(pattern="HH:mm:ss")
    private Date userTime;

    @JsonProperty("GuestTime")
    @JsonFormat(pattern="HH:mm:ss")
    private Date guestTime;

    @JsonProperty("UserTotal")
    private int userTotal;

    @JsonProperty("GuestTotal")
    private int guestTotal;

    @JsonProperty("Earning")
    private BigDecimal earning;

    @JsonProperty("CountSub")
    private int subCount;

    @JsonProperty("Schedule")
    private Schedule schedule;

    @JsonProperty("Date")
    private Date statisticDate;
}
