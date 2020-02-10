package help_desk_app.dao.impl;

import help_desk_app.dao.CommentDao;
import help_desk_app.entity.Comment;
import org.hibernate.SessionFactory;

public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao {

    public CommentDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        super.setAClass(Comment.class);
    }
}
