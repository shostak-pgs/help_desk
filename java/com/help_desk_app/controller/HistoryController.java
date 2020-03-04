package com.help_desk_app.controller;

import com.help_desk_app.dto.HistoryDto;
import com.help_desk_app.service.HistoryService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HistoryDto>> getComments(@PathVariable(value = "id") final Long id) throws ServiceException {
        return ResponseEntity.ok(new ArrayList<HistoryDto>());
        //return ResponseEntity.ok(historyService.getAllHistoriesByTicketId(id));
    }

}