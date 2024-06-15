package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.v2.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
