package com.bottle.event.service;

import com.bottle.event.model.entity.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class EventRegistrationService {
    private EventRegistrationService() {
    }

    private static EventRegistrationService instance = null;

    public static EventRegistrationService getEventRegistrationService() {
        if (instance == null) {
            instance = new EventRegistrationService();
        }
        return instance;
    }

    public String registrationEvent(Map<String, String[]> paramMap) {
        String resultData;

        String text = paramMap.get("text")[0];
        String startTimeS = paramMap.get("start_time")[0];
        String endTimeS = paramMap.get("end_time")[0];
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.ENGLISH);
            startTime.setTime(dateFormat.parse(startTimeS));
            endTime.setTime(dateFormat.parse(endTimeS));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Event event = new Event();
        event.setText(text);
        event.setStartTime(startTime);
        event.setEndTime(endTime);

        resultData = "complete";
        return resultData;
    }
}
