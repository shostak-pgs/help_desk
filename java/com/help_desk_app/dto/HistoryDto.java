package com.help_desk_app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.Objects;

public class HistoryDto {

    @JsonFormat(pattern = "dd/MM/yyyy" )
    private Date actionDate;

    private String action;

    private String user;

    private String description;

    public HistoryDto() {
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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
        HistoryDto that = (HistoryDto) o;
        return Objects.equals(actionDate, that.actionDate) &&
                Objects.equals(action, that.action) &&
                Objects.equals(user, that.user) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionDate, action, user, description);
    }

    @Override
    public String toString() {
        return "HistoryDto{" +
                "date=" + actionDate +
                ", action='" + action + '\'' +
                ", user='" + user + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
