package com.example.helloservlet.web.frontcontroller.v4;

import com.example.helloservlet.web.frontcontroller.MyView;
import com.example.helloservlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.example.helloservlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.example.helloservlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "fronControllerServletV4", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-from", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        log.info("FrontControllerServletV4.service");

        String uri = req.getRequestURI();
        ControllerV4 controller = controllerMap.get(uri);
        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap
        Map<String, Object> model = new HashMap<>();
        Map<String, String> paramMap = createParamMap(req);
        String view = controller.process(paramMap, model);

        MyView mv = viewResolver(view);

        mv.render(model, req, resp);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames()
            .asIterator()
            .forEachRemaining(name -> paramMap.put(name, req.getParameter(name)));
        return paramMap;
    }

}
