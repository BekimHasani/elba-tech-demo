package com.solutions.elbaproficiencydemo.repository.impl;


import com.solutions.elbaproficiencydemo.repository.CustomBatchRepository;
import com.solutions.elbaproficiencydemo.repository.utils.BatchExecutor;
import com.solutions.elbaproficiencydemo.repository.utils.SpringContext;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional(propagation = Propagation.NEVER)
public class CustomBatchRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements CustomBatchRepository<T, ID> {
    private final EntityManager entityManager;


    public CustomBatchRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public <S extends T> List<S> saveInBatch(Iterable<S> entities) {
        if (entities == null) {
            throw new IllegalArgumentException("The given Iterable of entities cannot be null");
        }

        BatchExecutor batchExecutor = SpringContext.getBean(BatchExecutor.class);
        return batchExecutor.saveInBatch(entities);
    }
}
