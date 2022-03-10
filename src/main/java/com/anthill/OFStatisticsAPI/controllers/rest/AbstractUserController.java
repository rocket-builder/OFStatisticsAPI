package com.anthill.OFStatisticsAPI.controllers.rest;

import com.anthill.OFStatisticsAPI.beans.ScheduleStatistic;
import com.anthill.OFStatisticsAPI.beans.dto.ManagerOnlyFansModelsStatisticDto;
import com.anthill.OFStatisticsAPI.beans.dto.ManagerWorkersStatisticDto;
import com.anthill.OFStatisticsAPI.beans.dto.SignUpDto;
import com.anthill.OFStatisticsAPI.beans.dto.TotalWithSchedulesDto;
import com.anthill.OFStatisticsAPI.beans.onlyfans.OnlyFansModel;
import com.anthill.OFStatisticsAPI.beans.user.AbstractUser;
import com.anthill.OFStatisticsAPI.beans.user.Admin;
import com.anthill.OFStatisticsAPI.beans.user.Manager;
import com.anthill.OFStatisticsAPI.beans.user.Worker;
import com.anthill.OFStatisticsAPI.controllers.AbstractController;
import com.anthill.OFStatisticsAPI.enums.AccountType;
import com.anthill.OFStatisticsAPI.enums.Role;
import com.anthill.OFStatisticsAPI.exceptions.*;
import com.anthill.OFStatisticsAPI.repos.*;
import com.anthill.OFStatisticsAPI.services.CalculateStatisticService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import security.MD5;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "User")
@RequestMapping("/user")
@RestController
public class AbstractUserController extends AbstractController<AbstractUser, AbstractUserRepos> {

    private final AdminRepos adminRepos;
    private final WorkerRepos workerRepos;
    private final ManagerRepos managerRepos;
    private final OnlyFansModelRepos modelRepos;
    private final ScheduleStatisticRepos statisticRepos;
    private final OnlyFansAccountRepos accountRepos;
    private final CalculateStatisticService calculateStatisticService;

    protected AbstractUserController(AbstractUserRepos repos, AdminRepos adminRepos, WorkerRepos workerRepos,
                                     ManagerRepos managerRepos, OnlyFansModelRepos modelRepos,
                                     ScheduleStatisticRepos statisticRepos,
                                     OnlyFansAccountRepos accountRepos,
                                     CalculateStatisticService calculateStatisticService) {
        super(repos);
        this.adminRepos = adminRepos;
        this.workerRepos = workerRepos;
        this.managerRepos = managerRepos;
        this.modelRepos = modelRepos;
        this.statisticRepos = statisticRepos;
        this.accountRepos = accountRepos;
        this.calculateStatisticService = calculateStatisticService;
    }

    @PostMapping("/login")
    public AbstractUser login(@RequestBody AbstractUser auth) throws UserNotFoundedException, IncorrectPasswordException {
        var user = repos.findByLogin(auth.getLogin());

        if (user.isEmpty()){
            throw new UserNotFoundedException();
        }
        if(!user.get().getPassword().equals(MD5.getHash(auth.getPassword()))){
            throw new IncorrectPasswordException();
        }

        return user.get();
    }

    @PostMapping("/{role}/signUp")
    public AbstractUser signUp(@PathVariable("role") Role role, @RequestBody SignUpDto signUp)
            throws LoginAlreadyTakenException, IncorrectUserException {
        if(repos.existsByLogin(signUp.getLogin())){
            throw new LoginAlreadyTakenException();
        }

        var password = MD5.getHash(signUp.getPassword());

        switch (role){
            case ADMIN:{
                var admin = new Admin();
                admin.setLogin(signUp.getLogin());
                admin.setPassword(password);

                return adminRepos.save(admin);
            }
            case MANAGER:{
                var manager = new Manager();
                manager.setLogin(signUp.getLogin());
                manager.setPassword(password);

                return managerRepos.save(manager);
            }
            default:
                throw new IncorrectUserException();
        }
    }

    @PostMapping("/{id}/worker")
    public Worker saveWorker(@PathVariable("id") long id, @RequestBody Worker worker)
            throws ResourceNotFoundedException, LoginAlreadyTakenException {
        if(repos.existsByLogin(worker.getLogin())){
            throw new LoginAlreadyTakenException();
        }

        var manager = managerRepos.findById(id)
                .orElseThrow(ResourceNotFoundedException::new);

        worker.setManager(manager);
        return workerRepos.save(worker);
    }

    @GetMapping("/{id}/worker")
    public List<Worker> getWorkers(@PathVariable("id") long id)
            throws ResourceNotFoundedException {
        var manager = managerRepos.findById(id)
                .orElseThrow(ResourceNotFoundedException::new);

        return manager.getWorkers();
    }

    @PostMapping("/{id}/onlyFansModel")
    public OnlyFansModel saveModel(@PathVariable("id") long id, @RequestBody OnlyFansModel model)
            throws UserNotFoundedException {
        var manager = managerRepos.findById(id)
                .orElseThrow(UserNotFoundedException::new);

        model.setManager(manager);

        var saved = modelRepos.save(model);
        var accounts = saved.getAccounts().stream()
                .peek(a -> a.setModel(saved))
                .collect(Collectors.toList());

        accountRepos.saveAll(accounts);

        return saved;
    }

    @GetMapping("/{id}/onlyFansModel")
    public List<OnlyFansModel> getModels(@PathVariable("id") long id)
            throws ResourceNotFoundedException {
        var manager = managerRepos.findById(id)
                .orElseThrow(ResourceNotFoundedException::new);

        return manager.getModels();
    }

    @GetMapping("/{id}/onlyFansModel/{accountType}/schedule")
    public List<ScheduleStatistic> getSchedulesByAccountType(@PathVariable("id") long id,
                                                             @PathVariable("accountType") AccountType accountType,
                                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end)
            throws ResourceAlreadyExists {
        var manager = managerRepos.findById(id)
                .orElseThrow(ResourceAlreadyExists::new);

        return statisticRepos.findAllByManagerAndAccountTypeWithDateRange(manager, accountType, start, end);
    }

    @GetMapping("/{id}/onlyFansModel/mainStatistic")
    public ManagerOnlyFansModelsStatisticDto getManagerMainPageModelsStatistic(@PathVariable("id") long id,
                                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end)
            throws UserNotFoundedException, IncorrectOffsetException {
        var manager = managerRepos.findById(id)
                .orElseThrow(UserNotFoundedException::new);
        if(pageSize <= 0){
            throw new IncorrectOffsetException();
        }

        var models = statisticRepos.getDateRangeCalculatedManagerModelsStatistic(
                manager, start, end, PageRequest.of(page, pageSize));

        var total = statisticRepos.getCalculatedTotalStatisticByManager(
                manager, start, end);

        return ManagerOnlyFansModelsStatisticDto.builder()
                .total(total)
                .models(models)
                .build();
    }

    @GetMapping("/{id}/worker/mainStatistic")
    public ManagerWorkersStatisticDto getManagerMainPageWorkersStatistic(@PathVariable("id") long id,
                                                                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                                               @RequestParam(value = "page", defaultValue = "0") int page,
                                                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                                                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end)
            throws UserNotFoundedException, IncorrectOffsetException {
        var manager = managerRepos.findById(id)
                .orElseThrow(UserNotFoundedException::new);
        if(pageSize <= 0){
            throw new IncorrectOffsetException();
        }

        var total = statisticRepos.getCalculatedTotalStatisticByManager(
                manager, start, end);

        var workers = statisticRepos.getDateRangeCalculatedManagerWorkersStatistic(
                manager, start, end, PageRequest.of(page, pageSize));

        return ManagerWorkersStatisticDto.builder()
                .total(total)
                .workers(workers)
                .build();
    }

    @GetMapping("/{id}/schedules/mainStatistic")
    public TotalWithSchedulesDto getWorkerMainPageSchedules(@PathVariable("id") long id,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end)
            throws UserNotFoundedException {
        var worker = workerRepos.findById(id)
                .orElseThrow(UserNotFoundedException::new);

        var total = statisticRepos.getCalculatedTotalStatisticByWorker(
                worker, start, end);

        var schedules = statisticRepos.findAllByWorkerWithDateRange(
                worker, start, end);

        return TotalWithSchedulesDto.builder()
                .total(total)
                .statistics(schedules)
                .build();
    }
}
