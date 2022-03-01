package com.anthill.OFStatisticsAPI.repos;

import com.anthill.OFStatisticsAPI.beans.ScheduleStatistic;
import com.anthill.OFStatisticsAPI.interfaces.CommonRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleStatisticRepos extends CommonRepository<ScheduleStatistic> {

    @Query(value = "select * from schedule_statistic s where s.account_id = ?1 limit ?3 offset ?2", nativeQuery = true)
    List<ScheduleStatistic> findAllPageableByAccount(long accountId, int offset, int limit);
}
