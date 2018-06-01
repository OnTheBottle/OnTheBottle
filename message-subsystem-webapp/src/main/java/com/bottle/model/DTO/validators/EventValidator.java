package com.bottle.model.DTO.validators;

import com.bottle.model.DTO.Request.EventDTO;
import com.bottle.service.Utilities;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

@Component
public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return EventDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EventDTO eventDTO = (EventDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.empty", "Title must not be empty.");
        String title = eventDTO.getTitle();
        if ((title.length()) > 32) {
            errors.rejectValue("title", "title.tooLong", "Title must not more than 32 characters.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startTime", "time.empty", "Start time must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endTime", "time.empty", "End time must not be empty.");
        if (!eventDTO.getStartTime().isEmpty() && !eventDTO.getEndTime().isEmpty()) {
            checkTime(eventDTO.getStartTime(), eventDTO.getEndTime(), errors);
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "place", "place.empty", "Place must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "owner", "owner.empty", "Owner must not be empty.");
    }

    private void checkTime(String startTime, String endTime, Errors errors) {
        Date start = Utilities.formatDate(startTime);
        Date end = Utilities.formatDate(endTime);

        if (start.after(end)) {
            errors.rejectValue("startTime", "time.noValid", "Start time must be before end time.");
        }
    }
}
