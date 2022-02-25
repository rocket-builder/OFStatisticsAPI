package com.anthill.OFStatisticsAPI.controllers.rest;

import com.anthill.OFStatisticsAPI.beans.OnlyFansModel;
import com.anthill.OFStatisticsAPI.beans.Statistic;
import com.anthill.OFStatisticsAPI.controllers.AbstractController;
import com.anthill.OFStatisticsAPI.exceptions.ResourceNotFoundedException;
import com.anthill.OFStatisticsAPI.repos.OnlyFansModelRepos;
import com.anthill.OFStatisticsAPI.repos.StatisticRepos;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "OnlyFansModel")
@RequestMapping("/onlyFansModel")
@RestController
public class OnlyFansModelController extends AbstractController<OnlyFansModel, OnlyFansModelRepos> {

    private final StatisticRepos statisticRepos;

    protected OnlyFansModelController(OnlyFansModelRepos repos, StatisticRepos statisticRepos) {
        super(repos);
        this.statisticRepos = statisticRepos;
    }

    @PostMapping("/{id}/statistic")
    public Statistic saveStatisticForModel(@PathVariable("id") long id, @RequestBody Statistic statistic)
            throws ResourceNotFoundedException {
        var model = repos.findById(id);

        if(model.isEmpty()){
            throw new ResourceNotFoundedException();
        }

        statistic.setModel(model.get());
        return statisticRepos.save(statistic);
    }
}
