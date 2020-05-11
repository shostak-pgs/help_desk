package com.help_desk_app.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
    /**
     * Calls Dao get() method
     * @param id - id of entity
     * @return object of derived class Entity
     */
    T getOne(Serializable id);

    /**
     * Calls Dao create() method
     * @param entity - object of derived class Entity
     */
    T create(T entity);

    /**
     * Calls Dao update() method
     * @param entity - object of derived class Entity
     */
    void update(T entity);

    /**
     * Calls Dao delete() method
     * @param id - id of entity
     */
    void delete(Serializable id);

    /**
     * Calls Dao all() method
     * @return all entities of derived class Entity
     */
    List<T> getAll();
}
