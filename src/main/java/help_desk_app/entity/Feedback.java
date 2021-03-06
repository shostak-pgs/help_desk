package help_desk_app.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="FEEDBACK")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "rate")
    private Byte rate;

    @Column(name = "date")
    private Date date;

    @Column(name = "text")
    private String text;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public Feedback(){ }

    public Feedback(User user, Byte rate, Date date, String text, Ticket ticket) {
        this.user = user;
        this.rate = rate;
        this.date = date;
        this.text = text;
        this.ticket = ticket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Byte getRate() {
        return rate;
    }

    public void setRate(Byte rate) {
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(id, feedback.id) &&
                Objects.equals(user, feedback.user) &&
                Objects.equals(rate, feedback.rate) &&
                Objects.equals(date, feedback.date) &&
                Objects.equals(text, feedback.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, rate, date, text, ticket);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", rate=" + rate +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", ticket=" + ticket +
                '}';
    }
}
