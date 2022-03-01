package com.anthill.OFStatisticsAPI.beans.user;

import com.anthill.OFStatisticsAPI.beans.ScheduleStatistic;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@Entity
@DiscriminatorValue("worker")
@JsonIgnoreProperties({"manager", "statistics"})
public class Worker extends AbstractUser {

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    private double profitPercent;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL)
    private List<ScheduleStatistic> statistics = new ArrayList<>();
}
