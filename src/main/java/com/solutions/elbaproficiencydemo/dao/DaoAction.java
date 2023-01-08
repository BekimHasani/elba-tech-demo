package com.solutions.elbaproficiencydemo.dao;


import java.util.List;
import java.util.Set;

public class DaoAction<T> {

    private List<T> rowsToDelete;
    private Set<T> rowsToSave;

    public DaoAction(List<T> rowsToDelete, Set<T> rowsToSave) {
        this.rowsToDelete = rowsToDelete;
        this.rowsToSave = rowsToSave;
    }

    public List<T> getRowsToDelete() {
        return rowsToDelete;
    }

    public Set<T> getRowsToSave() {
        return rowsToSave;
    }
}
