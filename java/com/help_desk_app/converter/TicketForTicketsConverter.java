package com.help_desk_app.converter;

import com.help_desk_app.dto.TicketDtoForTickets;
import com.help_desk_app.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketForTicketsConverter {

    public TicketDtoForTickets toDto(Ticket ticket) {
        TicketDtoForTickets dto = new TicketDtoForTickets();
            dto.setId(ticket.getId());
            dto.setTicketName(ticket.getTicketName());
            dto.setDesiredResolutionDate(ticket.getDesiredResolutionDate());
            dto.setState(ticket.getState());
            dto.setUrgency(ticket.getUrgency());
            return dto;
    }
}
