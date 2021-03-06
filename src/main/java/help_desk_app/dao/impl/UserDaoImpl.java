package help_desk_app.dao.impl;

import help_desk_app.dao.UserDao;
import help_desk_app.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        super.setAClass(User.class);
    }
}
