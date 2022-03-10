package com.anthill.OFStatisticsAPI.beans.dto;

import com.anthill.OFStatisticsAPI.beans.user.Worker;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class WorkerShortStatisticDto {

    private Worker worker;

    private BigDecimal profit;
    private long userTotal, guestTotal, subCount;
}
