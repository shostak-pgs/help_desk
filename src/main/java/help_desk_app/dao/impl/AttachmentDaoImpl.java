package help_desk_app.dao.impl;

import help_desk_app.dao.AttachmentDao;
import help_desk_app.entity.Attachment;
import org.hibernate.SessionFactory;

public class AttachmentDaoImpl extends BaseDaoImpl<Attachment> implements AttachmentDao {

    public AttachmentDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        super.setAClass(Attachment.class);
    }
}
