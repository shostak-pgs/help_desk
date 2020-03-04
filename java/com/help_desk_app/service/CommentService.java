package com.help_desk_app.service;

import com.help_desk_app.converter.CommentConverter;
import com.help_desk_app.dao.CommentDao;
import com.help_desk_app.dto.CommentDto;
import com.help_desk_app.entity.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentDao commentDao;
    private final CommentConverter commentConverter;

    public CommentService(CommentDao commentDao, CommentConverter commentConverter) {
        this.commentDao = commentDao;
        this.commentConverter = commentConverter;
    }

    @Transactional
    public List<Comment> getAll() {
        return commentDao.getAll();
    }

    @Transactional
    public List<CommentDto> getAllCommentsByTicketId(Serializable id) {
        return getAll().stream()
                .filter(comment -> (comment.getTicket().getId() == id))
                .map(commentConverter::toDto)
                .collect(Collectors.toList());
    }

}


