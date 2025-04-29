package com.example.helloservlet.web.frontcontroller.v2.controller;

import com.example.helloservlet.web.frontcontroller.MyView;
import com.example.helloservlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

    private static final String VIEW_PATH = "/WEB-INF/views/new-form.jsp";

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        return new MyView(VIEW_PATH);
    }
}
