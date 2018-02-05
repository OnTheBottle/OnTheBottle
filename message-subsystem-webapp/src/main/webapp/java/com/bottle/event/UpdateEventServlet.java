package com.bottle.event;

import com.bottle.APIHandlerServlet;
import com.bottle.event.service.EventService;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class UpdateEventServlet extends APIHandlerServlet.APIRequestHandler  {
    private static final UpdateEventServlet instance = new UpdateEventServlet();

    public static UpdateEventServlet getInstance() {
        return instance;
    }

    private UpdateEventServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        EventService eventService = EventService.getEventService();

        JSONObject jsonObject = new JSONObject();
        String result = eventService.updateEvent(map);
        jsonObject.put("result", result);
        return jsonObject;
    }
}
