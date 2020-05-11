package com.help_desk_app.service;

import com.help_desk_app.dao.AttachmentDao;
import com.help_desk_app.entity.Attachment;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public class AttachmentService {

    private final AttachmentDao attachmentDao;

    public AttachmentService(AttachmentDao attachmentDao) {
        this.attachmentDao = attachmentDao;
    }

    @Transactional
    public List<Attachment> getAll() {
        return attachmentDao.getAll();
    }

    @Transactional
    public Attachment getAttachmentById(Long id) {
        return attachmentDao.getOne(id);
    }
}

