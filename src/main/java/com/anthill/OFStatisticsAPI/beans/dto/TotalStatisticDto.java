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

    public TotalStatisticDto(BigDecimal profit, long users, long guests, long subscribers) {
        this.profit = profit;
        this.users = (int)users;
        this.guests = (int)guests;
        this.subscribers = (int)subscribers;
        this.traffic = (int)users + (int)guests;
    }
}
