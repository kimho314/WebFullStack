package com.example.helloservlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import lombok.extern.slf4j.Slf4j;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 */
@Slf4j
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("[전체 파라미터 조회] - start");

        Enumeration<String> parameterNames = req.getParameterNames();
        parameterNames.asIterator()
            .forEachRemaining(param -> log.info("{} = {}", param, req.getParameter(param)));

        log.info("[전체 파라미터 조회] - end");

        log.info("[단일 파라미터 조회]");
        String username = req.getParameter("username");
        String age = req.getParameter("age");

        log.info("username = {}", username);
        log.info("age = {}", age);

        log.info("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = req.getParameterValues("username");
        for (String name : usernames) {
            log.info("name = {}", name);
        }

        resp.getWriter().write("ok");
    }
}
