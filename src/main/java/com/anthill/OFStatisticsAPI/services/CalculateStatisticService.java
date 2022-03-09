package com.anthill.OFStatisticsAPI.services;

import com.anthill.OFStatisticsAPI.beans.ScheduleStatistic;
import com.anthill.OFStatisticsAPI.beans.dto.ManagerOnlyFansModelsStatisticDto;
import com.anthill.OFStatisticsAPI.beans.dto.OnlyFansModelDailyStatisticDto;
import com.anthill.OFStatisticsAPI.beans.dto.OnlyFansModelShortStatisticDto;
import com.anthill.OFStatisticsAPI.beans.dto.TotalStatisticDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CalculateStatisticService {

    public OnlyFansModelDailyStatisticDto getDaily(List<ScheduleStatistic> statistics){
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

        return OnlyFansModelDailyStatisticDto.builder()
                .total(total)
                .statistics(statistics)
                .build();
    }
}
