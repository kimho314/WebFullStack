package com.example.helloservlet.web.servletmvc;

import com.example.helloservlet.domain.member.Member;
import com.example.helloservlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        Member member = new Member(username, age);
        Member savedMember = memberRepository.save(member);

        req.setAttribute("savedMember", savedMember);
        String viewPath = "/WEB-INF/views/save-result.jsp";
        req.getRequestDispatcher(viewPath)
            .forward(req, resp);
    }
}
