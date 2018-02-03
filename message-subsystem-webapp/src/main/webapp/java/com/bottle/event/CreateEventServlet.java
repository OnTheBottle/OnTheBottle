package com.bottle.event;

import com.bottle.APIHandlerServlet;
import com.bottle.event.service.EventRegistrationService;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CreateEventServlet extends APIHandlerServlet.APIRequestHandler  {
    private static final CreateEventServlet instance = new CreateEventServlet();

    public static CreateEventServlet getInstance() {
        return instance;
    }

    private CreateEventServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        EventRegistrationService eventRegistrationService = EventRegistrationService.getEventRegistrationService();

        JSONObject jsonObject = new JSONObject();
        String result = eventRegistrationService.registrationEvent(map);
        jsonObject.put("result", result);
        return jsonObject;
    }
}
