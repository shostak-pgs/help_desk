package com.help_desk_app.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.help_desk_app.entity.enums.Role;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;

    private String password;

    //@Fetch(value = FetchMode.JOIN)
   // @OneToMany(cascade = CascadeType.ALL)
   // @JoinColumn(name = "user_id")
    //@JsonManagedReference
  //  @JsonIdentityInfo(
  //          generator = ObjectIdGenerators.PropertyGenerator.class,
   //         property = "id")
   // private Set<Ticket> ticketList;

   // @Fetch(value = FetchMode.JOIN)
   // @OneToMany()
   // @JoinColumn(name = "user_id")
    //@OneToMany(mappedBy = "user")
   // private List<Feedback> feedbackList;

   // @Fetch(value = FetchMode.JOIN)
   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    //private List<Comment> commentList;

   // @Fetch(value = FetchMode.JOIN)
   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
   // private List<History> historyList;

    public User(){}

    public User(String firstName, String lastName, Role role, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.password = password;
        //ticketList = new ArrayList<>();
      //  commentList = new ArrayList<>();
        //historyList = new ArrayList<>();
       // feedbackList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //public Set<Ticket> getTicketList() {
    //    return ticketList;
    //}

   // public void setTicketList(Set<Ticket> ticketList) {
      //  this.ticketList = ticketList;
   // }


    //public List<Feedback> getFeedbackList() {
     //   return feedbackList;
   // }

   // public void setFeedbackList(List<Feedback> feedbackList) {
     //   this.feedbackList = feedbackList;
   // }

  //  public List<Comment> getCommentList() {
     //   return commentList;
    //}

   // public void setCommentList(List<Comment> commentList) {
   //     this.commentList = commentList;
   // }

    //public List<History> getHistoryList() {
    //    return historyList;
   // }

   // public void setHistoryList(List<History> historyList) {
     //   this.historyList = historyList;
  //  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                role == user.role &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, role, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
