package com.anthill.OFStatisticsAPI.beans;

import com.anthill.OFStatisticsAPI.beans.onlyfans.OnlyFansAccount;
import com.anthill.OFStatisticsAPI.beans.onlyfans.OnlyFansModel;
import com.anthill.OFStatisticsAPI.beans.user.Worker;
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
@JsonIgnoreProperties({"account", "worker"})
public class ScheduleStatistic extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private OnlyFansAccount account;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExtraStatistic> extraStatistic;

    @JsonFormat(pattern="HH:mm:ss")
    @Temporal(TemporalType.TIME)
    private Date userTime, guestTime;

    private int userTotal, guestTotal, subCount;

    private BigDecimal profit;

    private Schedule schedule;

    private Date moment;
}
