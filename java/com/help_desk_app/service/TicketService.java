package com.help_desk_app.service;

import com.help_desk_app.converter.TicketForOverviewConverter;
import com.help_desk_app.converter.TicketForTicketsConverter;
import com.help_desk_app.dao.TicketDao;
import com.help_desk_app.dto.TicketDtoForOverview;
import com.help_desk_app.dto.TicketDtoForTickets;
import com.help_desk_app.entity.Ticket;
import com.help_desk_app.validator.TicketValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketDao ticketDao;
    private final TicketForOverviewConverter ticketForOverviewConverter;
    private final TicketForTicketsConverter ticketForTicketsConverter;

    public TicketService(TicketDao ticketDao, TicketForOverviewConverter ticketForOverviewConverter, TicketForTicketsConverter ticketForTicketsConverter) {
        this.ticketDao = ticketDao;
        this.ticketForOverviewConverter = ticketForOverviewConverter;
        this.ticketForTicketsConverter = ticketForTicketsConverter;
    }

    @Transactional
    public List<Ticket> getAll() {
        return ticketDao.getAllForTickets();
    }

    @Transactional
    public Ticket getTicketById(Long id) {
        return ticketDao.getOne(id);
    }

    @Transactional
    public List<TicketDtoForTickets> getAllTicketsByUserId(Long id) {
        List<Ticket> allTickets = getAll();
        return allTickets.stream()
                .filter(ticket -> (ticket.getUser().getId() == id))
                .map(ticketForTicketsConverter::toDto)
                .collect(Collectors.toList());
        }

    @Transactional
    public TicketDtoForOverview getOneForOverview(Serializable id) {
        return ticketForOverviewConverter.toDto(ticketDao.getOneForOverview(id));
    }

    @Transactional
    public TicketDtoForTickets create(TicketDtoForOverview ticketDto) {
        ticketDto.setCreatedOn(new Date());
        TicketValidator.validateCreated(ticketDto);
        Ticket newTicket = ticketDao.create(ticketForOverviewConverter.fromDto(ticketDto));
        return ticketForTicketsConverter.toDto(newTicket);
    }

    @Transactional
    public TicketDtoForTickets update(TicketDtoForOverview ticketDto, Long id) {
        TicketValidator.validate(ticketDto);
        Ticket ticket = getTicketById(id);
        ticketForOverviewConverter.copyNonNullProperties(ticketDto, ticket);
        ticketDao.update(ticket);
        return ticketForTicketsConverter.toDto(ticket);
    }
}
