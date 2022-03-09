package com.anthill.OFStatisticsAPI.repos;

import com.anthill.OFStatisticsAPI.beans.ScheduleStatistic;
import com.anthill.OFStatisticsAPI.beans.onlyfans.OnlyFansModel;
import com.anthill.OFStatisticsAPI.beans.user.Manager;
import com.anthill.OFStatisticsAPI.enums.AccountType;
import com.anthill.OFStatisticsAPI.interfaces.CommonRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleStatisticRepos extends CommonRepository<ScheduleStatistic> {

    @Query(value = "select * from schedule_statistic s where s.account_id = ?1 limit ?3 offset ?2", nativeQuery = true)
    List<ScheduleStatistic> findAllPageableByAccount(long accountId, int offset, int limit);

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
}
