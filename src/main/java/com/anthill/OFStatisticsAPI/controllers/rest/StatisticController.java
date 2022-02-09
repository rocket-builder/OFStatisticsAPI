package com.anthill.OFStatisticsAPI.controllers.rest;

import com.anthill.OFStatisticsAPI.beans.Statistic;
import com.anthill.OFStatisticsAPI.controllers.AbstractController;
import com.anthill.OFStatisticsAPI.repos.StatisticRepos;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Statistic")
@RequestMapping("/statistic")
@RestController
public class StatisticController extends AbstractController<Statistic, StatisticRepos> {

    protected StatisticController(StatisticRepos repos) {
        super(repos);
    }
}
