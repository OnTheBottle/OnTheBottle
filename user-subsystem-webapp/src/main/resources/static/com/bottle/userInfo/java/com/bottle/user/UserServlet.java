package com.bottle.user;

import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

import com.bottle.user.model.DAO.*;
import com.bottle.user.model.entity.*;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("MainServlet servlet is working!");
        JSONStreamAware responseJSONStreamAware;
        JSONObject resultJSONObject = new JSONObject();

        UserDAOImpl userDAO = new UserDAOImpl();
        UserEntity user = null;

        user = (UserEntity)userDAO.getEntityByID(3);

        JSONObject userJson = new JSONObject();
        userJson.put("id", user.getId());
        userJson.put("name", user.getName());
        userJson.put("surname", user.getSurname());
        userJson.put("age", user.getAge());


        resultJSONObject.put("result",userJson);
        System.out.print("resultJSONObject is ");
        System.out.println(resultJSONObject.toString());
        responseJSONStreamAware = resultJSONObject;
        try (Writer writer = resp.getWriter()) {
            responseJSONStreamAware.writeJSONString(writer);
        }
    }

}
