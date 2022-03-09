package com.anthill.OFStatisticsAPI.beans.dto;

import com.anthill.OFStatisticsAPI.beans.onlyfans.OnlyFansModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class OnlyFansModelShortStatisticDto {

    private OnlyFansModel model;
    private BigDecimal profit;

    private long userTotal, guestTotal, subCount;
}
