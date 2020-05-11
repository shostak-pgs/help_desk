package com.help_desk_app.entity;

import javax.persistence.*;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Blob blob;

    @ManyToMany(mappedBy = "attachments")
    private Set<Ticket> ticket = new HashSet<>();

    private String name;

    public Attachment(){ }

    public Attachment(Blob blob, String name) {
        this.blob = blob;
        //this.ticket = ticket;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }

    public Set<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(Set<Ticket> ticket) {
        this.ticket = ticket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(blob, that.blob) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticket, name);
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", blob=" + blob +
                ", ticketId=" + ticket +
                ", name='" + name + '\'' +
                '}';
    }
}
