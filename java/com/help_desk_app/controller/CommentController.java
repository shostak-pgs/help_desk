package com.help_desk_app.controller;

import com.help_desk_app.dto.CommentDto;
import com.help_desk_app.service.CommentService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/app/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentDto>> getComments(@NotBlank(message = "The id cannot be blank") @PathVariable(value = "id") final Long id) throws ServiceException {
        return ResponseEntity.ok(commentService.getAllCommentsByTicketId(id));
    }

}
