package com.example.helloservlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printRequest(req);
        printHeaders(req);
    }

    private void printRequest(HttpServletRequest req) {
        log.info("--- REQUEST-LINE - start ---");

        log.info("request.getMethod() = " + req.getMethod());
        log.info("request.getProtocol() = " + req.getProtocol());
        log.info("reqeust.getScheme() = " + req.getScheme());
        log.info("request.getRequestURL() = " + req.getRequestURL());
        log.info("request.getRequestURI() = " + req.getRequestURI());
        log.info("request.getQueryString() = " + req.getQueryString());
        log.info("request.isSecure() = " + req.isSecure());

        log.info("--- REQUEST-LINE - end ---");
    }

    private void printHeaders(HttpServletRequest req) {
        log.info("--- Headers - start ---");

//        Enumeration<String> headerNames = req.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String element = headerNames.nextElement();
//            log.info(element + " : " + element);
//        }

        req.getHeaderNames().asIterator()
            .forEachRemaining(headerName -> log.info(headerName + " = " + req.getHeader(headerName)));

        log.info("--- Headers - end ---");
    }
}
