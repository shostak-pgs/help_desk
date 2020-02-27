package com.help_desk_app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.help_desk_app.entity.Attachment;
import com.help_desk_app.entity.enums.Category;
import com.help_desk_app.entity.enums.State;
import com.help_desk_app.entity.enums.Urgency;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class TicketDtoForOverview {
    private Long id;

    private String ticketName;

    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy" )
    private Date createdOn;

    @JsonFormat(pattern = "dd/MM/yyyy" )
    private Date desiredResolutionDate;

    private String assigneeName;

    private String approverName;

    private String userName;

    private State state;

    private Category category;

    private Urgency urgency;

    private Set<Attachment> attachments;

    public TicketDtoForOverview(){}

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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getDesiredResolutionDate() {
        return desiredResolutionDate;
    }

    public void setDesiredResolutionDate(Date desiredResolutionDate) {
        this.desiredResolutionDate = desiredResolutionDate;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDtoForOverview that = (TicketDtoForOverview) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ticketName, that.ticketName) &&
                Objects.equals(description, that.description) &&
                Objects.equals(createdOn, that.createdOn) &&
                Objects.equals(desiredResolutionDate, that.desiredResolutionDate) &&
                Objects.equals(assigneeName, that.assigneeName) &&
                Objects.equals(approverName, that.approverName) &&
                Objects.equals(userName, that.userName) &&
                state == that.state &&
                category == that.category &&
                urgency == that.urgency &&
                Objects.equals(attachments, that.attachments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketName, description, createdOn, desiredResolutionDate, assigneeName, approverName, userName, state, category, urgency, attachments);
    }

    @Override
    public String toString() {
        return "TicketDtoForOverview{" +
                "id=" + id +
                ", ticketName='" + ticketName + '\'' +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", desiredResolutionDate=" + desiredResolutionDate +
                ", assigneeName='" + assigneeName + '\'' +
                ", approverName='" + approverName + '\'' +
                ", userName=" + userName +
                ", state=" + state +
                ", category=" + category +
                ", urgency=" + urgency +
                ", attachments=" + attachments +
                '}';
    }
}
