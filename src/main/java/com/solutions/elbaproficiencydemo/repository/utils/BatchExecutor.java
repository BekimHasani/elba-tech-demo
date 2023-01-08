package com.solutions.elbaproficiencydemo.repository.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class BatchExecutor<T> {

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    private final EntityManagerFactory entityManagerFactory;

    public BatchExecutor(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public <S extends T> List<S> saveInBatch(Iterable<S> entities) {
        if (entities == null) {
            throw new IllegalArgumentException("The given Iterable of entities must not be null");
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction eTransaction = entityManager.getTransaction();

        List<S> savedEntities = new ArrayList<>();

        try {
            eTransaction.begin();
            int i = 0;
            for (S entity : entities) {
                savedEntities.add(entity);
                if (i % batchSize == 0 && i > 0) {
                    eTransaction.commit();
                    entityManager.clear();
                    eTransaction.begin();
                }
                entityManager.merge(entity);
                i++;
            }
            eTransaction.commit();
        } catch (RuntimeException e) {
            if (eTransaction.isActive()) {
                eTransaction.rollback();
            }
            throw e;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return savedEntities;
    }
}
