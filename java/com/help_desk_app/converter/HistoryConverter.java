package com.help_desk_app.converter;

import com.help_desk_app.dto.HistoryDto;
import com.help_desk_app.entity.History;
import org.springframework.stereotype.Component;

@Component
public class HistoryConverter {

    public HistoryDto toDto(History history) {
        HistoryDto dto = new HistoryDto();
        dto.setActionDate(history.getDate());
        dto.setUser(history.getUser().getFirstName() + " " + history.getUser().getLastName());
        dto.setAction(history.getAction());
        dto.setDescription(history.getDescription());
        return dto;
    }

}
