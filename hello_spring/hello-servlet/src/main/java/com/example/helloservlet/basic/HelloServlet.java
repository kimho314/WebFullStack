package com.example.helloservlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("HelloServlet.service");
        log.info("request = {}", req);
        log.info("response = {}", resp);

        String username = req.getParameter("username");
        log.info("username = {}", username);

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write("hello " + username);
    }
}
