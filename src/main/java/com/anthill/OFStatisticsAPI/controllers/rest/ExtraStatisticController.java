package com.anthill.OFStatisticsAPI.controllers.rest;

import com.anthill.OFStatisticsAPI.beans.ExtraStatistic;
import com.anthill.OFStatisticsAPI.controllers.AbstractController;
import com.anthill.OFStatisticsAPI.repos.ExtraStatisticRepos;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ExtraStatistic")
@RequestMapping("/extraStatistic")
@RestController
public class ExtraStatisticController extends AbstractController<ExtraStatistic, ExtraStatisticRepos> {

    protected ExtraStatisticController(ExtraStatisticRepos repos) {
        super(repos);
    }
}
