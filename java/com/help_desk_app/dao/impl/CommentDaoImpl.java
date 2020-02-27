package com.help_desk_app.dao.impl;

import com.help_desk_app.dao.CommentDao;
import com.help_desk_app.entity.Comment;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao {

    public CommentDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        super.setAClass(Comment.class);
    }
}
