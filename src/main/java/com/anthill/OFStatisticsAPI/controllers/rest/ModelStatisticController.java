package com.anthill.OFStatisticsAPI.controllers.rest;

import com.anthill.OFStatisticsAPI.beans.ModelStatistic;
import com.anthill.OFStatisticsAPI.controllers.AbstractController;
import com.anthill.OFStatisticsAPI.repos.ModelStatisticRepos;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ModelStatistic")
@RequestMapping("/modelStatistic")
@RestController
public class ModelStatisticController extends AbstractController<ModelStatistic, ModelStatisticRepos> {

    protected ModelStatisticController(ModelStatisticRepos repos) {
        super(repos);
    }
}
