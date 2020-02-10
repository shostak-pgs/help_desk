package help_desk_app.dao.impl;

import help_desk_app.dao.HistoryDao;
import help_desk_app.entity.History;
import org.hibernate.SessionFactory;

public class HistoryDaoImpl extends BaseDaoImpl<History> implements HistoryDao {

    public HistoryDaoImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory);
        super.setAClass(History.class);
    }
}
