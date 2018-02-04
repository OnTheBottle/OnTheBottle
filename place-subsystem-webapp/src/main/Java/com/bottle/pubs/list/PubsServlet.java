package com.bottle.pubs.list;

import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class PubsServlet {
    protected JSONStreamAware processRequest(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();

        JSONObject jsonObject = new JSONObject();
        List<Pub> pubs = new ArrayList<Pub>();
        try {
           //connect to db
            pubs = HibernateFactory.getInstance().getPubsDAO().getAllPubs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put("pub_list", pubs);
        return jsonObject;
    }
}
