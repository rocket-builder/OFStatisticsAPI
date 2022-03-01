package com.anthill.OFStatisticsAPI.repos;

import com.anthill.OFStatisticsAPI.beans.user.AbstractUser;
import com.anthill.OFStatisticsAPI.interfaces.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AbstractUserRepos extends CommonRepository<AbstractUser> {

    Optional<AbstractUser> findByLogin(String login);
    boolean existsByLogin(String login);
}
