package com.example.helloservlet.web.frontcontroller.v3;

import com.example.helloservlet.web.frontcontroller.ModelView;
import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
