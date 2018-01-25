package com.bottle.gateway.conf;

import lombok.Data;

/**
 * Created by Sergey on 26.01.2017.
 */
@Data
public class WebApp {
    private Rest rest = new Rest();

    @Data
    public static class Rest {
        private Http http = new Http();
    }

    @Data
    public static class Http {
        private String host;
        private int port;
    }
}