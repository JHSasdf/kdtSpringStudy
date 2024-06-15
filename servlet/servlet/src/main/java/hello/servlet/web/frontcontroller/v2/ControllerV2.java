package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.Myview;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.server.ServerCloneException;

public interface ControllerV2 {
    Myview process(HttpServletRequest request, HttpServletResponse response) throws ServerCloneException, IOException, ServletException;
}
