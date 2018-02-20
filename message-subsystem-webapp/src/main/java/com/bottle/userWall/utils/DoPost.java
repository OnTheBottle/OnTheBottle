package com.bottle.userWall.utils;


import com.bottle.userWall.dao.Factory;
import com.bottle.userWall.dao.Posts;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;


public class DoPost extends HttpServlet{

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            StringBuffer sb = new StringBuffer();
            String line = null;

            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                sb.append( line );

            try {
                JSONObject jsonObject = new JSONObject( sb.toString() );
                Posts post = new Posts();
                String text = jsonObject.getString( "text" );
                String imageadress = jsonObject.getString( "image" );
                String security = jsonObject.getString( "security" );
            //    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy.MM.dd.HH.mm.ss" );
            //    Timestamp timestamp = new Timestamp( System.currentTimeMillis() );
                Date now=new Date();  //sdf.format( timestamp );
                post.setText( text );
                post.setImage( imageadress );
                post.setDate(now);
                post.setSecurity( security );
                try {
                    Factory.getInstance().getPostDAO().addPost( post );
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            catch (JSONException e) {
            }

            String greetings = "Post add successed";
            JSONObject result = new JSONObject();
            response.setContentType("charset=UTF-8");
            PrintWriter out = response.getWriter();
            result.put("result", greetings);
            out.println(result.toString());

        }

}

