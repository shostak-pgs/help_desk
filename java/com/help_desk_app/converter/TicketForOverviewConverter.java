package com.help_desk_app.converter;

import com.help_desk_app.dto.TicketDtoForOverview;
import com.help_desk_app.entity.Ticket;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Component
public class TicketForOverviewConverter {

    public TicketDtoForOverview toDto(Ticket ticket) {
        TicketDtoForOverview dto = new TicketDtoForOverview();
        copyNonNullProperties(ticket, dto);
        if (ticket.getApprover() != null) {
            dto.setUserName(ticket.getUser().getFirstName() + " " + ticket.getUser().getLastName());
        }
        if (ticket.getApprover() != null) {
            dto.setApproverName(ticket.getApprover().getFirstName() + " " + ticket.getApprover().getLastName());
        }
        if (ticket.getApprover() != null) {
            dto.setAssigneeName(ticket.getAssignee().getFirstName() + " " + ticket.getAssignee().getLastName());
        }

        return dto;
    }

    public Ticket fromDto(TicketDtoForOverview dto) {
        Ticket ticket = new Ticket();
        copyNonNullProperties(dto, ticket);
        return ticket;
    }

    public void copyNonNullProperties(Object source, Object destination){
        BeanUtils.copyProperties(source, destination,
                getNullPropertyNames(source));
    }

    private String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<Object> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
