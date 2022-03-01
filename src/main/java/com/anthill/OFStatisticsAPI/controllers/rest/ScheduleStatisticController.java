package com.anthill.OFStatisticsAPI.controllers.rest;

import com.anthill.OFStatisticsAPI.beans.ScheduleStatistic;
import com.anthill.OFStatisticsAPI.controllers.AbstractController;
import com.anthill.OFStatisticsAPI.repos.ScheduleStatisticRepos;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ScheduleStatistic")
@RequestMapping("/scheduleStatistic")
@RestController
public class ScheduleStatisticController extends AbstractController<ScheduleStatistic, ScheduleStatisticRepos> {

    protected ScheduleStatisticController(ScheduleStatisticRepos repos) {
        super(repos);
    }
}
