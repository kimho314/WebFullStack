package com.example.helloservlet.web.servlet;

import com.example.helloservlet.domain.member.Member;
import com.example.helloservlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

@Slf4j
@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        log.info("MemberSaveServlet.service");
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        Member member = new Member(username, age);
        Member savedMember = memberRepository.save(member);

        resp.setContentType(MediaType.TEXT_HTML_VALUE);
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter w = resp.getWriter();
        w.write("""
            <html>
            <head>
                <meta charset="UTF-8">
            </head>
            <body>
            <h2>성공</h2>
            <ul>
                <li>id=%s</li>
                <li>username=%s</li>
                <li>age=%s</li>
            </ul>
            <a href=/index.html>메인</a>
            </body>
            </html>
            """.formatted(member.getId(), savedMember.getUsername(), savedMember.getAge()));
    }
}
