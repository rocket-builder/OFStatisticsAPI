package com.anthill.OFStatisticsAPI.controllers.rest;

import com.anthill.OFStatisticsAPI.beans.ScheduleStatistic;
import com.anthill.OFStatisticsAPI.beans.dto.OnlyFansModelDailyStatisticDto;
import com.anthill.OFStatisticsAPI.beans.dto.TotalStatisticDto;
import com.anthill.OFStatisticsAPI.beans.onlyfans.OnlyFansAccount;
import com.anthill.OFStatisticsAPI.beans.onlyfans.OnlyFansModel;
import com.anthill.OFStatisticsAPI.controllers.AbstractController;
import com.anthill.OFStatisticsAPI.enums.AccountType;
import com.anthill.OFStatisticsAPI.exceptions.ResourceNotFoundedException;
import com.anthill.OFStatisticsAPI.repos.OnlyFansAccountRepos;
import com.anthill.OFStatisticsAPI.repos.OnlyFansModelRepos;
import com.anthill.OFStatisticsAPI.repos.ScheduleStatisticRepos;
import com.anthill.OFStatisticsAPI.services.CalculateStatisticService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Tag(name = "OnlyFansModel")
@RequestMapping("/onlyFansModel")
@RestController
public class OnlyFansModelController extends AbstractController<OnlyFansModel, OnlyFansModelRepos> {

    private final ScheduleStatisticRepos statisticRepos;
    private final OnlyFansAccountRepos accountRepos;
    private final CalculateStatisticService calculateStatisticService;

    protected OnlyFansModelController(OnlyFansModelRepos repos, ScheduleStatisticRepos statisticRepos,
                                      OnlyFansAccountRepos accountRepos,
                                      CalculateStatisticService calculateStatisticService) {
        super(repos);
        this.statisticRepos = statisticRepos;
        this.accountRepos = accountRepos;
        this.calculateStatisticService = calculateStatisticService;
    }

    @GetMapping("/{id}/{accountType}/dailyStatistic")
    public OnlyFansModelDailyStatisticDto getDailyStatistic(@PathVariable("id") long id,
                                                     @PathVariable("accountType") AccountType accountType,
                                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date moment)
            throws ResourceNotFoundedException {

        var model = repos.findById(id).orElseThrow(ResourceNotFoundedException::new);
        var statistics = statisticRepos.findAllByModelAndAccountTypeWithDate(model, accountType, moment);

        return calculateStatisticService.getDaily(statistics);
    }

    @PostMapping("{id}/account")
    public OnlyFansAccount saveAccount(@PathVariable("id") long id, @RequestBody OnlyFansAccount account) throws ResourceNotFoundedException {
        var modelOptional = repos.findById(id);

        return modelOptional
                .map(model -> {
                    account.setModel(model);
                    return accountRepos.save(account);
                })
                .orElseThrow(
                        ResourceNotFoundedException::new);

    }
}
