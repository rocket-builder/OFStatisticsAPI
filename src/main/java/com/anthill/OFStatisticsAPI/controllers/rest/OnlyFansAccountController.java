package com.anthill.OFStatisticsAPI.controllers.rest;

import com.anthill.OFStatisticsAPI.beans.ScheduleStatistic;
import com.anthill.OFStatisticsAPI.beans.onlyfans.OnlyFansAccount;
import com.anthill.OFStatisticsAPI.controllers.AbstractController;
import com.anthill.OFStatisticsAPI.exceptions.IncorrectOffsetException;
import com.anthill.OFStatisticsAPI.exceptions.ResourceNotFoundedException;
import com.anthill.OFStatisticsAPI.repos.OnlyFansAccountRepos;
import com.anthill.OFStatisticsAPI.repos.ScheduleStatisticRepos;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
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
        var account = repos.findById(id)
                .orElseThrow(ResourceNotFoundedException::new);

        scheduleStatistic.setAccount(account);
        return statisticRepos.save(scheduleStatistic);
    }

    @GetMapping("/{id}/statistic")
    public List<ScheduleStatistic> getStatisticForModel(@PathVariable("id") long id)
            throws ResourceNotFoundedException {
        var account = repos.findById(id)
                .orElseThrow(ResourceNotFoundedException::new);

        return account.getStatistics();
    }

    @GetMapping("/{id}/statistic/pageable")
    public List<ScheduleStatistic> getStatisticForModel(@PathVariable("id") long id,
                                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                        @RequestParam(value = "page", defaultValue = "0") int page)
            throws ResourceNotFoundedException, IncorrectOffsetException {
        var account = repos.findById(id)
                .orElseThrow(ResourceNotFoundedException::new);

        if(page < 0 || pageSize <= 0){
            throw new IncorrectOffsetException();
        }

        return statisticRepos.findAllByAccount(account, PageRequest.of(page, pageSize));
    }
}
