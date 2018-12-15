package com.webtaxi.servlets;

import javax.servlet.http.HttpServletResponse;

class ResponseContentTypeSetter {
    private ResponseContentTypeSetter() {

    }

    static void setContentType(HttpServletResponse resp) {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
    }
}
