package com.help_desk_app.controller;

import com.help_desk_app.dto.TicketDtoForOverview;
import com.help_desk_app.dto.TicketDtoForTickets;
import com.help_desk_app.entity.Ticket;
import com.help_desk_app.service.TicketService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ticket>> get() throws ServiceException {
            return ResponseEntity.ok(ticketService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketDtoForOverview> get(@PathVariable(value = "id") final Long id) throws ServiceException {
        return ResponseEntity.ok(ticketService.getOneForOverview(id));
        //return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketDtoForTickets> create(@RequestBody final TicketDtoForOverview dto) {
        TicketDtoForTickets created = ticketService.create(dto);
        final URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(created.getId())
                        .toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketDtoForTickets> update(final HttpServletRequest request,
                                       @PathVariable(value = "id") final Long id,
                                       @RequestBody final TicketDtoForOverview dto
                                      ) throws URISyntaxException {
        TicketDtoForTickets updated = ticketService.update(dto, id);
        return ResponseEntity.ok().location(new URI(request.getRequestURI())).body(updated);
    }
}
