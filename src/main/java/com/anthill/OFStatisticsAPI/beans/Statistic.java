package com.anthill.OFStatisticsAPI.beans;

import com.anthill.OFStatisticsAPI.enums.Schedule;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@Entity
@JsonIgnoreProperties({"model"})
public class Statistic extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "model_id")
    private OnlyFansModel model;

    @JsonProperty("ExtraStatistics")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExtraStatistic> extraStatistic;

    @JsonProperty("UserTime")
    @JsonFormat(pattern="HH:mm:ss")
    @Temporal(TemporalType.TIME)
    private Date userTime;

    @JsonProperty("GuestTime")
    @JsonFormat(pattern="HH:mm:ss")
    @Temporal(TemporalType.TIME)
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
