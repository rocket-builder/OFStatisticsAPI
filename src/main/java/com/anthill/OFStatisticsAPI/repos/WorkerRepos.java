package com.anthill.OFStatisticsAPI.repos;

import com.anthill.OFStatisticsAPI.beans.user.Worker;
import com.anthill.OFStatisticsAPI.interfaces.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepos extends CommonRepository<Worker> {
}
