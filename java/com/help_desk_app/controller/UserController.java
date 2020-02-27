package com.help_desk_app.controller;

import com.help_desk_app.dto.TicketDtoForTickets;
import com.help_desk_app.entity.Ticket;
import com.help_desk_app.entity.User;
import com.help_desk_app.service.TicketService;
import com.help_desk_app.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/app/users")
public class UserController {

    private final UserService userService;
    private final TicketService ticketService;

    public UserController(UserService userService, TicketService ticketServic) {
        this.ticketService = ticketServic;
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> getUser(@NotBlank(message = "The id cannot be blank") @PathVariable(value = "id") final Long id) throws ServiceException {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @RequestMapping(value = "/{id}/tickets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TicketDtoForTickets>> get(@PathVariable Long id) throws ServiceException {
        return ResponseEntity.ok(ticketService.getAllTicketsByUserId(id));
    }
}
