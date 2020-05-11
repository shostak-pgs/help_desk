package com.help_desk_app.entity.enums;

public enum Category {
    APPLICATION_AND_SERVERS("Application & Services"),
    BENEFITS_AND_PAPER_WORK("Benefits & Paper Work"),
    HARDWARE_AND_SOFTWARE("Hardware & Software"),
    PEOPLE_MANAGEMENT("People Management"),
    SECURITY_AND_ACCESS("Security & Access"),
    WORKPLACES_AND_FACILITIES("Workplaces & Facilities");

    private String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}