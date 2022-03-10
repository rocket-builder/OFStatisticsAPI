package com.anthill.OFStatisticsAPI.controllers.rest;

import com.anthill.OFStatisticsAPI.beans.ScheduleStatistic;
import com.anthill.OFStatisticsAPI.controllers.AbstractController;
import com.anthill.OFStatisticsAPI.exceptions.ResourceNotFoundedException;
import com.anthill.OFStatisticsAPI.repos.ScheduleStatisticRepos;
import com.anthill.OFStatisticsAPI.repos.WorkerRepos;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ScheduleStatistic")
@RequestMapping("/scheduleStatistic")
@RestController
public class ScheduleStatisticController extends AbstractController<ScheduleStatistic, ScheduleStatisticRepos> {

    private final WorkerRepos workerRepos;

    protected ScheduleStatisticController(ScheduleStatisticRepos repos, WorkerRepos workerRepos) {
        super(repos);
        this.workerRepos = workerRepos;
    }


    @PostMapping("/{id}/assign")
    public ScheduleStatistic assignWorkerToSchedule(
            @PathVariable("id") long id, @RequestParam long workerId) throws ResourceNotFoundedException {
        var scheduleOptional = repos.findById(id);

        var schedule = scheduleOptional
                .orElseThrow(ResourceNotFoundedException::new);

        var worker = workerRepos.findById(workerId)
                .orElseThrow(ResourceNotFoundedException::new);

        schedule.setWorker(worker);

        return repos.save(schedule);
    }
}
