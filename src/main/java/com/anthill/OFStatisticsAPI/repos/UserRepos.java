package com.anthill.OFStatisticsAPI.repos;

import com.anthill.OFStatisticsAPI.beans.User;
import com.anthill.OFStatisticsAPI.interfaces.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepos extends CommonRepository<User> {

    Optional<User> findByLogin(String login);
}
