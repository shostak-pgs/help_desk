package help_desk_app.dao.impl;

import help_desk_app.dao.FeedbackDao;
import help_desk_app.entity.Feedback;
import org.hibernate.SessionFactory;

public class FeedbackDaoImpl extends BaseDaoImpl<Feedback> implements FeedbackDao {

    public FeedbackDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        super.setAClass(Feedback.class);
    }
}
