package com.help_desk_app.dao.impl;

import com.help_desk_app.dao.HistoryDao;
import com.help_desk_app.entity.History;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryDaoImpl extends BaseDaoImpl<History> implements HistoryDao {

    public HistoryDaoImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory);
        super.setAClass(History.class);
    }
}
