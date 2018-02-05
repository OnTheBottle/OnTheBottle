package com.bottle.user;

import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ShowAnimals in MainServlet servlet is working!");
        JSONStreamAware responseJSONStreamAware;
        JSONObject resultJSONObject = new JSONObject();
        JSONObject animalsJSONObject = new JSONObject();

 //       ArrayList<Animal> animalsList = Service.serviceShowAnimals();

 //       for(int i= 0; i<animalsList.size(); i++) {
 //           JSONObject animalJson = new JSONObject();
 //           animalJson.put("name", animalsList.get(i).getAnimalName());
 //           animalJson.put("age", animalsList.get(i).getAnimalAge());
 //           animalJson.put("class", animalsList.get(i).getAnimalClass());
 //           animalsJSONObject.put(i, animalJson);
 //       }

//        resultJSONObject.put("result",animalsJSONObject);
 //       System.out.print("resultJSONObject is ");
  //      System.out.println(resultJSONObject.toString());
  //      responseJSONStreamAware = resultJSONObject;
  //      try (Writer writer = resp.getWriter()) {
  //          responseJSONStreamAware.writeJSONString(writer);
  //      }
    }

}
