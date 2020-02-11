package com.help_desk_app.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="HISTORY")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Column(name = "date")
    private Date date;

    @Column(name = "action")
    private String action;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "description")
    private String description;

    public History(){}

    public History(Ticket ticket, Date date, String action, String description) {
        this.ticket = ticket;
        this.date = date;
        this.action = action;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(id, history.id) &&
                Objects.equals(date, history.date) &&
                Objects.equals(action, history.action) &&
                Objects.equals(description, history.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, action, description);
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", ticket=" + ticket +
                ", date=" + date +
                ", action='" + action + '\'' +
                //", userId=" + userId +
                ", description='" + description + '\'' +
                '}';
    }
}
