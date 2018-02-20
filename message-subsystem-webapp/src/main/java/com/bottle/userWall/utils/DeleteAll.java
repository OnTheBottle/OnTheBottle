package com.bottle.userWall.utils;

import com.bottle.userWall.dao.Factory;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteAll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
          Factory.getInstance().getPostDAO().deleteAll();
        } catch (Exception e) {
            System.out.println( e.toString() );
        }

        String otchet = "Все записи удалены";
        JSONObject result = new JSONObject();
        response.setContentType("charset=UTF-8");
        PrintWriter out = response.getWriter();
        result.put("result", otchet);
        out.println(result.toString());


    }
}
