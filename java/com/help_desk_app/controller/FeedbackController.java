package com.help_desk_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.help_desk_app.dto.FeedbackDto;
import com.help_desk_app.service.FeedbackService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URI;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/app/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<FeedbackDto> getFeedback(@NotBlank(message = "The id cannot be blank") @PathVariable(value = "id") final Long id) throws ServiceException {
        return ResponseEntity.ok(feedbackService.findOneByTicketId(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> createFeedback(
            @NotNull(message = "The feedback cannot be null") @Valid @RequestBody final String dto) throws IOException {
        System.out.println(dto);
        final URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/")
                        .buildAndExpand(feedbackService.create(new ObjectMapper().readValue(dto, FeedbackDto.class)))
                        .toUri();

        return ResponseEntity.created(uri).build();
    }

}
