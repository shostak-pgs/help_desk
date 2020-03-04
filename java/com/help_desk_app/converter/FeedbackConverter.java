package com.help_desk_app.converter;

import com.help_desk_app.dto.FeedbackDto;
import com.help_desk_app.entity.Feedback;
import com.help_desk_app.entity.User;
import com.help_desk_app.service.TicketService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FeedbackConverter {

    private TicketService ticketService;

    public FeedbackConverter(TicketService ticketService){
        this.ticketService = ticketService;
    }

    public FeedbackDto toDto(Feedback feedback) {
        FeedbackDto dto = new FeedbackDto();
        dto.setRate(feedback.getRate());
        dto.setText(feedback.getText());
        dto.setTicketId(feedback.getTicket().getId());
        return dto;
    }

    public Feedback fromDto(FeedbackDto dto) {
        return new Feedback(new User(), dto.getRate(), new Date(), dto.getText(), ticketService.getTicketById(dto.getTicketId()));
    }
}
