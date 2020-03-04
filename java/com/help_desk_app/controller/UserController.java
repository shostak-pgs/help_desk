package com.help_desk_app.controller;

import com.help_desk_app.config.security.CustomUserPrincipal;
import com.help_desk_app.dto.TicketDtoForTickets;
import com.help_desk_app.dto.UserCredentialDto;
import com.help_desk_app.dto.UserDto;
import com.help_desk_app.entity.User;
import com.help_desk_app.service.TicketService;
import com.help_desk_app.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final TicketService ticketService;

    public UserController(UserService userService, TicketService ticketServic) {
        this.ticketService = ticketServic;
        this.userService = userService;
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody final String userDto) {
        //new UserValidatorImpl().validate(userDto);
        User ud = ((CustomUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        return ResponseEntity.ok(ud);
    }

    @RequestMapping(value = "/{id}/tickets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TicketDtoForTickets>> get(@PathVariable Long id) throws ServiceException {
        return ResponseEntity.ok(ticketService.getAllTicketsByUserId(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUser(@PathVariable(value = "id") final Long id) throws ServiceException {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
