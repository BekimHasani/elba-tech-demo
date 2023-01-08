package com.solutions.elbaproficiencydemo.repository.utils;

import com.solutions.elbaproficiencydemo.dao.DaoAction;
import com.solutions.elbaproficiencydemo.entity.Department;
import com.solutions.elbaproficiencydemo.entity.Employee;
import org.apache.commons.compress.utils.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EntityFilter<T> {

    public static <T> DaoAction filterDuplicateEntities(Iterable<T> itr, List<T> existingEntities) {
        List<T> newEntityList = Lists.newArrayList(itr.iterator());

        Set<T> mergedEntityList = new HashSet<>();

        if (existingEntities.size() == 0) {
            mergedEntityList.addAll(newEntityList);
        }

        for (Object newEntity : newEntityList) {

            if (existingEntities.contains(newEntity)) {

                for (int i = 0; i < existingEntities.size(); i++) {

                    T existingEntity = existingEntities.get(i);
                    if (newEntity.equals(existingEntity)) {

                        if (newEntity instanceof Employee) {
                            Employee existingEmp = (Employee) existingEntity;
                            Employee newEmp = (Employee) newEntity;
                            mergedEntityList.add((T) existingEmp.update(newEmp));
                        }
                        if (newEntity instanceof Department) {
                            Department existingDep = (Department) existingEntity;
                            Department newDep = (Department) newEntity;
                            mergedEntityList.add((T) existingDep.update(newDep));
                        }
                        existingEntities.remove(i);
                        i += existingEntities.size();
                    }
                }
            } else {
                mergedEntityList.add((T) newEntity);
            }
        }

        return new DaoAction<T>(existingEntities, mergedEntityList);
    }
}
