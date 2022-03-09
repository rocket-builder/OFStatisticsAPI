package com.anthill.OFStatisticsAPI.beans.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TotalStatisticDto {

    private BigDecimal profit;
    private int traffic, users, guests, subscribers;
}
