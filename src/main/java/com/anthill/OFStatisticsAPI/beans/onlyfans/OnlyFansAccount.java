package com.anthill.OFStatisticsAPI.beans.onlyfans;

import com.anthill.OFStatisticsAPI.beans.AbstractEntity;
import com.anthill.OFStatisticsAPI.beans.ScheduleStatistic;
import com.anthill.OFStatisticsAPI.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@Entity
@JsonIgnoreProperties({"model", "statistics"})
public class OnlyFansAccount extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id")
    private OnlyFansModel model;

    private String login, password;

    private AccountType accountType;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<ScheduleStatistic> statistics;
}
