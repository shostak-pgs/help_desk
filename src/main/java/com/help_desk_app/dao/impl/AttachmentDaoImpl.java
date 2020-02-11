package com.help_desk_app.dao.impl;

import com.help_desk_app.dao.AttachmentDao;
import com.help_desk_app.entity.Attachment;
import org.hibernate.SessionFactory;

public class AttachmentDaoImpl extends BaseDaoImpl<Attachment> implements AttachmentDao {

    public AttachmentDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        super.setAClass(Attachment.class);
    }
}
