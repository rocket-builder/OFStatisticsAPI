package com.anthill.OFStatisticsAPI.controllers.rest;

import com.anthill.OFStatisticsAPI.beans.ScheduleStatistic;
import com.anthill.OFStatisticsAPI.beans.onlyfans.OnlyFansAccount;
import com.anthill.OFStatisticsAPI.controllers.AbstractController;
import com.anthill.OFStatisticsAPI.exceptions.ResourceNotFoundedException;
import com.anthill.OFStatisticsAPI.repos.OnlyFansAccountRepos;
import com.anthill.OFStatisticsAPI.repos.ScheduleStatisticRepos;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "OnlyFansAccount")
@RequestMapping("/onlyFansAccount")
@RestController
public class OnlyFansAccountController extends AbstractController<OnlyFansAccount, OnlyFansAccountRepos> {

    private final ScheduleStatisticRepos statisticRepos;

    protected OnlyFansAccountController(OnlyFansAccountRepos repos, ScheduleStatisticRepos statisticRepos) {
        super(repos);
        this.statisticRepos = statisticRepos;
    }

    @PostMapping("/{id}/statistic")
    public ScheduleStatistic saveStatisticForAccount(@PathVariable("id") long id,
                                                     @RequestBody ScheduleStatistic scheduleStatistic)
            throws ResourceNotFoundedException {
        var accountOptional = repos.findById(id);

        return accountOptional
                .map(account -> {
                    scheduleStatistic.setAccount(account);
                    return statisticRepos.save(scheduleStatistic);
                })
                .orElseThrow(
                        ResourceNotFoundedException::new);
    }

    @GetMapping("/{id}/statistic")
    public List<ScheduleStatistic> getStatisticForModel(@PathVariable("id") long id)
            throws ResourceNotFoundedException {
        var accountOptional = repos.findById(id);

        return accountOptional
                .map(OnlyFansAccount::getStatistics)
                .orElseThrow(ResourceNotFoundedException::new);
    }

    @GetMapping("/{id}/statistic/offset")
    public List<ScheduleStatistic> getStatisticForModel(@PathVariable("id") long id,
                                                        @RequestParam("start") int start, @RequestParam("end") int end)
            throws ResourceNotFoundedException {
        var accountOptional = repos.findById(id);

        return accountOptional
                .map(OnlyFansAccount::getStatistics)
                .orElseThrow(ResourceNotFoundedException::new);
    }
}
