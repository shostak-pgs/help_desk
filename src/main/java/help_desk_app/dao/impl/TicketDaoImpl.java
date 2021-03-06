package help_desk_app.dao.impl;

import help_desk_app.dao.TicketDao;
import help_desk_app.entity.Ticket;
import org.hibernate.SessionFactory;

public class TicketDaoImpl extends BaseDaoImpl<Ticket> implements TicketDao {

    public TicketDaoImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory);
        super.setAClass(Ticket.class);
    }
}
