package com.anthill.OFStatisticsAPI.beans.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ManagerWorkersStatisticDto {

    private TotalStatisticDto total;
    private List<WorkerShortStatisticDto> workers;
}
