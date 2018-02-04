package com.bottle.messenger;

import com.bottle.messenger.hibernate.model.Message;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class MessengerServlet {

    private static final MessengerServlet instance = new MessengerServlet();

    public static MessengerServlet getInstance(){
        return instance;
    }

    public JSONStreamAware processRequest(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        JSONObject response = new JSONObject();
        List<Message> messages = new ArrayList<Message>();
        try {
//            messages = ...;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.put("messages", messages);
        return response;
    }
}