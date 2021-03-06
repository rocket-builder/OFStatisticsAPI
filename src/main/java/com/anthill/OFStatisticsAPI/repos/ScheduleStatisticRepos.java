package com.anthill.OFStatisticsAPI.repos;

import com.anthill.OFStatisticsAPI.beans.ScheduleStatistic;
import com.anthill.OFStatisticsAPI.beans.dto.OnlyFansModelShortStatisticDto;
import com.anthill.OFStatisticsAPI.beans.dto.TotalStatisticDto;
import com.anthill.OFStatisticsAPI.beans.dto.WorkerShortStatisticDto;
import com.anthill.OFStatisticsAPI.beans.onlyfans.OnlyFansAccount;
import com.anthill.OFStatisticsAPI.beans.onlyfans.OnlyFansModel;
import com.anthill.OFStatisticsAPI.beans.user.Manager;
import com.anthill.OFStatisticsAPI.beans.user.Worker;
import com.anthill.OFStatisticsAPI.enums.AccountType;
import com.anthill.OFStatisticsAPI.interfaces.CommonRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleStatisticRepos extends CommonRepository<ScheduleStatistic> {

    List<ScheduleStatistic> findAllByAccount(OnlyFansAccount account, Pageable pageable);

    @Query(value = "select s from ScheduleStatistic s where s.account.model.manager=:manager and " +
            "s.account.accountType=:accountType and " +
            "s.moment > :start and s.moment < :end")
    List<ScheduleStatistic> findAllByManagerAndAccountTypeWithDateRange(@Param("manager")Manager manager,
                                                                    @Param("accountType") AccountType accountType,
                                                                    @Param("start") Date start, @Param("end") Date end);

    @Query(value = "select s from ScheduleStatistic s where s.account.model=:model " +
            "and s.account.accountType=:accountType and date(s.moment)=date(:moment)")
    List<ScheduleStatistic> findAllByModelAndAccountTypeWithDate(@Param("model")OnlyFansModel model,
                                                                 @Param("accountType") AccountType accountType,
                                                                 @Param("moment") Date moment);

    @Query(value = "select new com.anthill.OFStatisticsAPI.beans.dto.OnlyFansModelShortStatisticDto(" +
            "s.account.model, sum(s.profit), sum(s.subCount), sum(s.guestTotal), sum(s.userTotal)) " +
            "from ScheduleStatistic s where s.account.model.manager=:manager " +
            "and s.moment > :start and s.moment < :end " +
            "group by s.account.model")
    List<OnlyFansModelShortStatisticDto> getDateRangeCalculatedManagerModelsStatistic(@Param("manager") Manager manager,
                                                                                      @Param("start") Date start, @Param("end") Date end,
                                                                                      Pageable pageable);

    @Query(value = "select new com.anthill.OFStatisticsAPI.beans.dto.WorkerShortStatisticDto(" +
            "s.worker, sum(s.profit), sum(s.subCount), sum(s.guestTotal), sum(s.userTotal)) " +
            "from ScheduleStatistic s where s.account.model.manager=:manager " +
            "and s.moment > :start and s.moment < :end " +
            "group by s.worker")
    List<WorkerShortStatisticDto> getDateRangeCalculatedManagerWorkersStatistic(@Param("manager") Manager manager,
                                                                                @Param("start") Date start, @Param("end") Date end,
                                                                                Pageable pageable);

    @Query(value = "select new com.anthill.OFStatisticsAPI.beans.dto.TotalStatisticDto(" +
            "sum(s.profit), sum(s.guestTotal), sum(s.userTotal), sum(s.subCount))" +
            "from ScheduleStatistic s where s.account.model.manager=:manager " +
            "and s.moment > :start and s.moment < :end")
    TotalStatisticDto getCalculatedTotalStatisticByManager(@Param("manager") Manager manager,
                                                           @Param("start") Date start, @Param("end") Date end);

    @Query(value = "select new com.anthill.OFStatisticsAPI.beans.dto.TotalStatisticDto(" +
            "sum(s.profit), sum(s.guestTotal), sum(s.userTotal), sum(s.subCount))" +
            "from ScheduleStatistic s where s.worker=:worker " +
            "and s.moment > :start and s.moment < :end")
    TotalStatisticDto getCalculatedTotalStatisticByWorker(@Param("worker") Worker worker,
                                                          @Param("start") Date start, @Param("end") Date end);

    @Query(value = "select s from ScheduleStatistic s where s.worker=:worker " +
            "and s.moment > :start and s.moment < :end")
    List<ScheduleStatistic> findAllByWorkerWithDateRange(@Param("worker") Worker worker,
                                                         @Param("start") Date start, @Param("end") Date end);

    @Query(value = "select s from ScheduleStatistic s where s.account.model=:model " +
            "and s.account.accountType=:accountType " +
            "and s.moment > :start and s.moment < :end")
    List<ScheduleStatistic> findAllByModelAndAccountTypeWithDateRange(@Param("model") OnlyFansModel model,
                                                                      @Param("accountType") AccountType accountType,
                                                                      @Param("start") Date start, @Param("end") Date end);
}
