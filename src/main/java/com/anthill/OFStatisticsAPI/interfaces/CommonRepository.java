package com.anthill.OFStatisticsAPI.interfaces;

import com.anthill.OFStatisticsAPI.beans.AbstractEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface CommonRepository<E extends AbstractEntity> extends CrudRepository<E, Long> {

    Optional<E> findById(long id);
    List<E> findAll();
}
