package com.help_desk_app.service;

import com.help_desk_app.converter.HistoryConverter;
import com.help_desk_app.dao.HistoryDao;
import com.help_desk_app.dto.HistoryDto;
import com.help_desk_app.entity.History;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryService {

    private final HistoryDao historyDao;
    private final HistoryConverter historyConverter;

    public HistoryService(HistoryDao historyDao, HistoryConverter historyConverter) {
        this.historyDao = historyDao;
        this.historyConverter = historyConverter;
    }

    @Transactional
    public List<History> getAll() {
        return historyDao.getAll();
    }

    @Transactional
    public List<HistoryDto> getAllHistoriesByTicketId(Long id) {
        return getAll().stream()
                .filter(history -> (history.getTicket().getId() == id))
                .map(historyConverter::toDto)
                .collect(Collectors.toList());
    }

}

