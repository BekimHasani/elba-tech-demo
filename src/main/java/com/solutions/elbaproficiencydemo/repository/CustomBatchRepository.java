package com.solutions.elbaproficiencydemo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface CustomBatchRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    <S extends T> List<S> saveInBatch(Iterable<S> entities);
}
