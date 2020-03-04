package com.help_desk_app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.help_desk_app.entity.enums.State;
import com.help_desk_app.entity.enums.Urgency;
import java.util.Date;
import java.util.Objects;

public class TicketDtoForTickets {
    private Long id;
    private String ticketName;

    @JsonFormat(pattern = "dd/MM/yyyy" )
    private Date desiredResolutionDate;
    private State state;
    private Urgency urgency;

    public TicketDtoForTickets(Long id, String ticketName, Date desiredResolutionDate, State state, Urgency urgency) {
        this.id = id;
        this.ticketName = ticketName;
        this.desiredResolutionDate = desiredResolutionDate;
        this.state = state;
        this.urgency = urgency;
    }

    public TicketDtoForTickets() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public Date getDesiredResolutionDate() {
        return desiredResolutionDate;
    }

    public void setDesiredResolutionDate(Date desiredResolutionDate) {
        this.desiredResolutionDate = desiredResolutionDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDtoForTickets that = (TicketDtoForTickets) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ticketName, that.ticketName) &&
                Objects.equals(desiredResolutionDate, that.desiredResolutionDate) &&
                state == that.state &&
                urgency == that.urgency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketName, desiredResolutionDate, state, urgency);
    }

    @Override
    public String toString() {
        return "TicketDtoForTickets{" +
                "id=" + id +
                ", ticketName='" + ticketName + '\'' +
                ", desiredResolutionDate=" + desiredResolutionDate +
                ", state=" + state +
                ", urgency=" + urgency +
                '}';
    }
}
