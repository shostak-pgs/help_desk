package com.help_desk_app.entity;

import com.help_desk_app.entity.enums.Category;
import com.help_desk_app.entity.enums.State;
import com.help_desk_app.entity.enums.Urgency;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="TICKET")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String description;
    private Date createdOn;
    private Date desiredResolutionDate;
    private Long assigneeId;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private State state;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Urgency urgency;

    @Column(name = "approver_id")
    private Long approverId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket")
    private List<Attachment> attachmentList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket")
    private List<Comment> commentList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket")
    private List<History> historyList;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

}
