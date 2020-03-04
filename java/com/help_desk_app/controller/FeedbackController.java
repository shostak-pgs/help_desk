package com.help_desk_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.help_desk_app.dto.FeedbackDto;
import com.help_desk_app.service.FeedbackService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.net.URI;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FeedbackDto> getFeedback(@PathVariable(value = "id") final Long id) throws ServiceException {
        return ResponseEntity.ok(feedbackService.findOneByTicketId(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createFeedback(@RequestBody final String dto) throws IOException {
        final URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/")
                        .buildAndExpand(feedbackService.create(new ObjectMapper().readValue(dto, FeedbackDto.class)))
                        .toUri();

        return ResponseEntity.created(uri).build();
    }

}
