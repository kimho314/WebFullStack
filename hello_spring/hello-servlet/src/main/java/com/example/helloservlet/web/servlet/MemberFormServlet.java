package com.example.helloservlet.web.servlet;

import com.example.helloservlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import org.springframework.http.MediaType;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.setContentType(MediaType.TEXT_HTML_VALUE);
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter w = resp.getWriter();
        w.write("""
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <title>Title</title>
            </head>
            <body>
            <form action="/servlet/members/save" method="post">
                username: <input type="text" name="username"/>
                age:      <input type="text" name="age"/>
                <button type=submit>전송</button>
            </form>
            </body>
            </html>
            """);
    }
}
