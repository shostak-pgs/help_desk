package com.help_desk_app.dao.impl;

import com.help_desk_app.dao.TicketDao;
import com.help_desk_app.entity.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Repository
public class TicketDaoImpl extends BaseDaoImpl<Ticket> implements TicketDao {
    private static final String GET_ALL = "from ";

    @PersistenceContext
    private EntityManager entityManager;
    private SessionFactory sessionFactory;

    public TicketDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        super.setAClass(Ticket.class);
        this.sessionFactory = sessionFactory;
    }

    public List<Ticket> getAllForTickets() {
        EntityGraph entityGraph = entityManager.getEntityGraph("ticket-entity-graph-for-tickets");
        List<Ticket> t = sessionFactory.getCurrentSession().createQuery(GET_ALL + (Ticket.class).getName()).setHint("javax.persistence.fetchgraph", entityGraph).list();
        return t;
    }

    public Ticket getOneForOverview(Serializable id) {
        Ticket t = sessionFactory.getCurrentSession().find(Ticket.class, id, Collections.singletonMap("javax.persistence.fetchgraph", sessionFactory.getCurrentSession().getEntityGraph("ticket-entity-graph-for-overview")));
       return t;
    }
}
