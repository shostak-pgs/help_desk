package com.help_desk_app.validator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.help_desk_app.dto.TicketDtoForOverview;
import com.help_desk_app.entity.Attachment;
import com.help_desk_app.entity.enums.Category;
import com.help_desk_app.entity.enums.Urgency;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Component
public class TicketValidator {
    private final static String COMMENTS = "comment";
    private final static String DESCRIPTION = "description";
    private final static String NAME = "name";
    private final static String INVALID_NAME_CHARACTERS =
            "Invalid characters in %s. It is allowed to enter lowercase English alpha characters, " +
            "digits and special characters: ~.\"(),:;<>@[]!#$%&'*+-/=?^_`{|}";
    private final static String INVALID_COMMENTS_DESCRIPTION_CHARACTERS =
            "Invalid characters in %s. It is allowed to enter upper and lowercase English alpha characters, " +
            "digits and special characters: ~.\"(),:;<>@[]!#$%&'*+-/=?^_`{|}";
    private final static String INVALID_LENGTH = "The maximum number of characters in %s is %d";
    private final static String NAME_PATTERN = "^[a-z0-9~.\"(),:;<>@\\[\\]!#$%&'*+-/=?^_`{|}]+$";
    private final static String DESCRIPTION_COMMENTS_PATTERN = "^[a-zA-Z0-9~.\"(),:;<>@[\\]!#$%&'*\\+\\-\\/=?^_`{|}]+$";
    private final static String INVALID_ATTACHMENTS_FORMAT =
            "The selected file type is not allowed. Please select a " +
            "file of one of the following types: pdf, png, doc, docx, jpg, jpeg";
    private final static String INVALID_ATTACHMENTS_SIZE =
            "The size of attached file should not be greater than 5 Mb. Please select another file";
    private final static String INVALID_DATE =
            "The data can't be less than current date";
    private final static Set<String> FORMATS = new HashSet<>(Arrays.asList("pdf", "doc", "docx", "png", "jpg", "jpeg"));
    private static final String REQUIRED_FIELDS_EXCEPTION = "Name, Urgency and Category are required fields for ticket";
    private static final String INVALID_CATEGORY = "No such category exist";
    private static final String INVALID_URGENCY = "No such urgency exist";

    public static void validateCreated(@NotNull TicketDtoForOverview dto) {
        validateRequiredFields(dto);
        validate(dto);
    }

    public static void validate(@NotNull TicketDtoForOverview dto) {
        if(dto.getTicketName() != null)validateName(dto.getTicketName());
        if(dto.getDescription() != null) validateDescription(dto.getDescription());
        //if (dto.getComment() != null) validateComment(dto.getComment);
        if (dto.getUrgency() != null) validateUrgency(dto.getUrgency());
        if (dto.getCategory() != null) validateCategories(dto.getCategory());
        if (dto.getAttachments() != null) validateAttachment(dto.getAttachments());
        if(dto.getDesiredResolutionDate() != null) validateDesiredResolutionDate(dto.getDesiredResolutionDate());

    }

    private static void validateRequiredFields(TicketDtoForOverview dto) {
        if ((dto.getCategory() == null) || (dto.getUrgency() == null) || (dto.getTicketName() == null)) {
            throw  new IllegalArgumentException(REQUIRED_FIELDS_EXCEPTION);
        }
    }

    public static void validateName(String name) {
        validateLength(name, 100, INVALID_LENGTH, NAME);
        validateCharacters(name, NAME_PATTERN, INVALID_NAME_CHARACTERS, NAME);
    }

    public static void validateDescription(String description) {
        validateLength(description, 500, INVALID_LENGTH, DESCRIPTION);
        validateCharacters(description, DESCRIPTION_COMMENTS_PATTERN, INVALID_COMMENTS_DESCRIPTION_CHARACTERS, DESCRIPTION);
    }


    public static void validateDesiredResolutionDate(Date date) {
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
       // try {
          //  Date userDate = formatter.parse(String.valueOf(date));
           // Date currentDate = formatter.parse(String.valueOf(new Date()));
            if (date.before(new Date())) {
                throw new IllegalArgumentException(INVALID_DATE);
            }
       // } catch (ParseException e){
            //    throw new IllegalArgumentException(e.getMessage());
          //  }
    }

    public static void validateComment(String comment) {
        validateLength(comment, 500, INVALID_LENGTH, COMMENTS);
        validateCharacters(comment, DESCRIPTION_COMMENTS_PATTERN, INVALID_COMMENTS_DESCRIPTION_CHARACTERS, COMMENTS);
    }

    public static void validateAttachment(Set<Attachment> attachments) {
        for(Attachment attachment : attachments){
            if(!FORMATS.contains(attachment.getBlob())) {
                throw new IllegalArgumentException(INVALID_ATTACHMENTS_FORMAT);
            }
            try {
                if (attachment.getBlob().length() > 5000000) {
                    throw new IllegalArgumentException(INVALID_ATTACHMENTS_SIZE);
                }
            } catch (SQLException e){
                throw new IllegalArgumentException(e.getMessage());
            }
        }
    }

    public static void validateCategories (Category category) {
        if(!((Arrays.asList(Category.values())).contains(category))) {
            throw new IllegalArgumentException(INVALID_CATEGORY);
        }
    }

    public static void validateUrgency (Urgency urgency) {
        if(!((Arrays.asList(Urgency.values())).contains(urgency))) {
            throw new IllegalArgumentException(INVALID_CATEGORY);
        }
    }

    private static void validateCharacters(String value, String pattern,
                                           String exception, String object) {
        if (!Pattern.matches(pattern, value)) {
            throw new IllegalArgumentException(String.format(exception, object));
        }
    }

    private static void validateLength(String value, int maxLength,
                                       String exception, String object) {
        if(value.length() > maxLength) {
            throw new IllegalArgumentException(String.format(exception, object, maxLength));
        }
    }

}
