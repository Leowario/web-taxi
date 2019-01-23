package com.webtaxi.servlets;

import javax.servlet.http.HttpServletResponse;

class ResponseContentTypeSetter {
    private ResponseContentTypeSetter() {

    }

    static void setTextType(HttpServletResponse resp) {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
    }

    static void setJsonType(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }
}
