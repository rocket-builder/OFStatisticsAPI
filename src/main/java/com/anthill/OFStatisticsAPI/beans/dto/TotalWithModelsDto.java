package com.anthill.OFStatisticsAPI.beans.dto;

import com.anthill.OFStatisticsAPI.beans.onlyfans.OnlyFansModel;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TotalWithModelsDto {

    private TotalStatisticDto total;
    private List<OnlyFansModel> models;
}
