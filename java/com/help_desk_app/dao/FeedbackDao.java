package com.help_desk_app.dao;

import com.help_desk_app.entity.Feedback;
import java.io.Serializable;
import java.util.Optional;

public interface FeedbackDao extends BaseDao<Feedback> {

    Optional<Feedback> findOneByTicketId(Serializable id);
}
