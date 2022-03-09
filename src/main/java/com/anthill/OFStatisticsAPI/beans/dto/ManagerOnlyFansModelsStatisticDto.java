package com.anthill.OFStatisticsAPI.beans.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ManagerOnlyFansModelsStatisticDto {

    private TotalStatisticDto total;
    private List<OnlyFansModelShortStatisticDto> models;
}
