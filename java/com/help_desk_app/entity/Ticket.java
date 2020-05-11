package com.help_desk_app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.help_desk_app.entity.enums.Category;
import com.help_desk_app.entity.enums.State;
import com.help_desk_app.entity.enums.Urgency;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.util.*;

@NamedEntityGraph(
    name = "ticket-entity-graph-for-overview",
    attributeNodes = {
        @NamedAttributeNode("ticketName"),
        @NamedAttributeNode("description"),
        @NamedAttributeNode("createdOn"),
        @NamedAttributeNode("desiredResolutionDate"),
        @NamedAttributeNode("state"),
        @NamedAttributeNode("urgency"),
        @NamedAttributeNode("category"),
        @NamedAttributeNode("attachments"),
        @NamedAttributeNode(value = "user", subgraph = "user-name"),
        @NamedAttributeNode(value = "approver", subgraph = "approver-name"),
        @NamedAttributeNode(value = "assignee", subgraph = "assignee-name"),
    },
    subgraphs = {
        @NamedSubgraph(
            name = "user-name",
            attributeNodes = {
                 @NamedAttributeNode("firstName"),
                 @NamedAttributeNode("lastName"),
            }
        ),
            @NamedSubgraph(
                    name = "approver-name",
                    attributeNodes = {
                            @NamedAttributeNode("firstName"),
                            @NamedAttributeNode("lastName"),
                    }
            ),
            @NamedSubgraph(
                    name = "assignee-name",
                    attributeNodes = {
                            @NamedAttributeNode("firstName"),
                            @NamedAttributeNode("lastName"),
                    }
            )
    }
)

@NamedEntityGraph(
        name = "ticket-entity-graph-for-tickets",
        attributeNodes = {
                @NamedAttributeNode("ticketName"),
                @NamedAttributeNode("id"),
                @NamedAttributeNode("desiredResolutionDate"),
                @NamedAttributeNode("state"),
                @NamedAttributeNode("urgency"),
        }
)
@Entity
@Table(name="Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticketName;

    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy" )
    private Date createdOn;

    @JsonFormat(pattern = "dd/MM/yyyy" )
    private Date desiredResolutionDate;

    @Fetch(value = FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id", updatable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private User assignee;

    @Fetch(value = FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approver_id", updatable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private User approver;

    @Fetch(value = FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    private State state;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Urgency urgency;

    @Fetch(value = FetchMode.JOIN)
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinTable(name = "ticket_attachment",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "attachment_id"))
    private Set<Attachment> attachments = new HashSet<>();

    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "ticket_id")
    //private Set<Comment> commentSet;

   // @Fetch(value = FetchMode.SUBSELECT)
   // @OneToMany(cascade = CascadeType.ALL)
   // @JoinColumn(name = "ticket_id")
   // private List<History> historyList;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Feedback feedback;

    public Ticket() {
    }

    public Ticket(String ticketName, String description, Date desiredResolutionDate, Category category, Urgency urgency) {
        this.ticketName = ticketName;
        this.description = description;
        this.desiredResolutionDate = desiredResolutionDate;
        this.category = category;
        this.urgency = urgency;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn(){
       return createdOn;
       // return new SimpleDateFormat("dd/MM/yyyy").parse(createdOn.toString());
    }

    public void setCreatedOn(Date createdOn) {
        //Date date = new SimpleDateFormat("dd/MM/yyyy").parse(createdOn);
        //System.out.println(date);
        this.createdOn = createdOn;
    }

    public Date getDesiredResolutionDate() {
        return desiredResolutionDate;
    }

    public void setDesiredResolutionDate(Date desiredResolutionDate)  {
        this.desiredResolutionDate = desiredResolutionDate;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

  //  public List<Comment> getCommentList() {
   //     return commentList;
    //}

  //  public void setCommentList(List<Comment> commentList) {
   //     this.commentList = commentList;
  //  }

   // public List<History> getHistoryList() {
   //     return historyList;
   // }

   // public void setHistoryList(List<History> historyList) {
  //      this.historyList = historyList;
  //  }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) &&
                ticketName.equals(ticket.ticketName) &&
                description.equals(ticket.description) &&
                Objects.equals(createdOn, ticket.createdOn) &&
                desiredResolutionDate.equals(ticket.desiredResolutionDate) &&
                user.equals(ticket.user) &&
                state == ticket.state &&
                category == ticket.category &&
                urgency == ticket.urgency &&
                Objects.equals(feedback, ticket.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketName, description, createdOn, desiredResolutionDate, user, state, category, urgency, feedback);
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketName='" + ticketName + '\'' +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", desiredResolutionDate=" + desiredResolutionDate +
                ", assignee=" + assignee +
                ", approver=" + approver +
                ", user=" + user +
                ", state=" + state +
                ", category=" + category +
                ", urgency=" + urgency +
                ", attachments=" + attachments +
                ", feedback=" + feedback +
                '}';
    }
}
