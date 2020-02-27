package com.help_desk_app.dao;

import com.help_desk_app.entity.Ticket;
import java.io.Serializable;
import java.util.List;

public interface TicketDao extends BaseDao<Ticket> {

    Ticket getOneForOverview(Serializable id);
    List<Ticket> getAllForTickets() ;
}
