package com.help_desk_app.converter;

import com.help_desk_app.dto.CommentDto;
import com.help_desk_app.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    public CommentDto toDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setUser(comment.getUser().getFirstName() + " " + comment.getUser().getLastName());
        dto.setText(comment.getText());
        dto.setDate(comment.getCreatedDate());
        return dto;
    }
}
