package com.help_desk_app.dao.impl;

import com.help_desk_app.dao.FeedbackDao;
import com.help_desk_app.entity.Feedback;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.Optional;

@Repository
public class FeedbackDaoImpl extends BaseDaoImpl<Feedback> implements FeedbackDao {
    private static final String TICKET_COLUMN = "ticket_id";
    private static final String SELECT_FEEDBACK_SQL_STATEMENT = "from Feedback where ticket_id = :ticket_id";

    private SessionFactory sessionFactory;

    public FeedbackDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        super.setAClass(Feedback.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Feedback> findOneByTicketId(Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        return (session.createQuery(SELECT_FEEDBACK_SQL_STATEMENT)
                .setParameter(TICKET_COLUMN, id).uniqueResultOptional());
    }
}

