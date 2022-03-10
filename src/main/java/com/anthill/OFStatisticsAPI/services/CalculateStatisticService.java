package com.anthill.OFStatisticsAPI.services;

import com.anthill.OFStatisticsAPI.beans.ScheduleStatistic;
import com.anthill.OFStatisticsAPI.beans.dto.TotalWithSchedulesDto;
import com.anthill.OFStatisticsAPI.beans.dto.TotalStatisticDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CalculateStatisticService {

    public TotalWithSchedulesDto getDaily(List<ScheduleStatistic> statistics){
        var totalProfit = statistics.stream()
                .map(ScheduleStatistic::getProfit)
                .reduce(BigDecimal::add)
                .get();
        var totalSubscribers = statistics.stream()
                .mapToInt(ScheduleStatistic::getSubCount)
                .sum();
        var totalGuests = statistics.stream()
                .mapToInt(ScheduleStatistic::getGuestTotal)
                .sum();
        var totalUsers = statistics.stream()
                .mapToInt(ScheduleStatistic::getUserTotal)
                .sum();
        var trafficTotal = totalGuests + totalUsers;

        var total = TotalStatisticDto.builder()
                .profit(totalProfit)
                .subscribers(totalSubscribers)
                .guests(totalGuests)
                .users(totalUsers)
                .traffic(trafficTotal)
                .build();

        return TotalWithSchedulesDto.builder()
                .total(total)
                .statistics(statistics)
                .build();
    }
}
