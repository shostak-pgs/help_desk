package com.help_desk_app.dao.impl;

import com.help_desk_app.dao.BaseDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
    private static final String GET_ALL = "from ";

    private final SessionFactory sessionFactory;
    private Class<T> aClass;

    public BaseDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setAClass(Class< T > clazzToSet){
        this.aClass = clazzToSet;
    }

    @Override
    public T getOne(Serializable id) {
        return (T) sessionFactory.getCurrentSession().get(aClass, id);
    }

    @Override
    public T create(T t) {
        sessionFactory.getCurrentSession().save(t);
        return t;
    }

    @Override
    public void update(T t) {
        sessionFactory.getCurrentSession().saveOrUpdate(t);
    }

    @Override
    public void delete(Serializable id) {
        T t = getOne(id);
        sessionFactory.getCurrentSession().delete(t);
    }

    @Override
    public List<T> getAll() {
        return  sessionFactory.getCurrentSession().createQuery(GET_ALL + aClass.getName()).list();
    }
}

