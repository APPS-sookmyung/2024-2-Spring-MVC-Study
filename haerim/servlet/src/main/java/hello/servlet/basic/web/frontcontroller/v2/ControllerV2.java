package hello.servlet.basic.web.frontcontroller.v1;

import hello.servlet.basic.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {

    // ControllerV1이랑 똑같은데 MyView 반환
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
