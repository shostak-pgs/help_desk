package com.help_desk_app.service;

import com.help_desk_app.converter.FeedbackConverter;
import com.help_desk_app.dao.FeedbackDao;
import com.help_desk_app.dto.FeedbackDto;
import com.help_desk_app.entity.Feedback;
import com.help_desk_app.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;

@Service
public class FeedbackService {
    private final FeedbackDao feedbackDao;
    private final FeedbackConverter  feedbackConverter;
    private final TicketService ticketService;

    public FeedbackService(FeedbackDao  feedbackDao,TicketService s ,FeedbackConverter  feedbackConverter) {
        this. feedbackDao =  feedbackDao;
        this. feedbackConverter =  feedbackConverter;
        this.ticketService = s;
    }

    @Transactional
    public FeedbackDto findOneByTicketId(Serializable id) {
       Feedback feedback = feedbackDao.findOneByTicketId(id).orElseThrow(() -> new ServiceException("Can't get Good"));
       return feedbackConverter.toDto(feedback);
    }

    @Transactional
    public Feedback create(FeedbackDto feedbackDto) {
       return feedbackDao.create(feedbackConverter.fromDto(feedbackDto));
    }

}
