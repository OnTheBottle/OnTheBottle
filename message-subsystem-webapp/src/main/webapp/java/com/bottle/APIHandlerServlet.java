package com.bottle;

import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class APIHandlerServlet extends HttpServlet {
    public abstract static class APIRequestHandler {
        protected abstract JSONStreamAware processRequest(HttpServletRequest request) throws Exception;
    }
}
