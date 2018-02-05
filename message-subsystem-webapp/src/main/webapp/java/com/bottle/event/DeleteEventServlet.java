package com.bottle.event;

import com.bottle.APIHandlerServlet;
import com.bottle.event.service.EventService;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class DeleteEventServlet extends APIHandlerServlet.APIRequestHandler  {
    private static final DeleteEventServlet instance = new DeleteEventServlet();

    public static DeleteEventServlet getInstance() {
        return instance;
    }

    private DeleteEventServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        EventService eventService = EventService.getEventService();

        JSONObject jsonObject = new JSONObject();
        String result = eventService.deleteEvent(map);
        jsonObject.put("result", result);
        return jsonObject;
    }
}
