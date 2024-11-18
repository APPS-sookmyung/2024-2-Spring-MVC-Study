package hello.servlet.basic.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV1 {

    // 서블릿과 똑같은 모양 구현.
    // 회원 폼 컨트롤러, 회원 저장 컨트롤러, 회원 리스트 컨트롤러 다 구현할 것.
    // 매핑 정보를 다형성과 일관성있게 찾을 수 있어야 함. (프론트 컨트롤러는 이 인터페이스를 호출해서 일관성있게 로직을 가져갈 것.)
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}