package com.anthill.OFStatisticsAPI.beans.dto;

import com.anthill.OFStatisticsAPI.beans.onlyfans.OnlyFansModel;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ScheduleStatisticWithModelsDto {

    private List<OnlyFansModel> models;
    private TotalStatisticDto total;
}
