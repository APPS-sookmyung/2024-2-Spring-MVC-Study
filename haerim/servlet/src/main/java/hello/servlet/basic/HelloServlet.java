package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // super.service(req, resp); // 서블릿이 호출되면 이 서비스 로직이 호출됨
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("request = " + response);

        String username = request.getParameter("username"); // 쿼리 파라미터를 쉽게 읽도록
        System.out.println("username = " + username);

        // 응답 메시지 생성 (HttpServletResponse) 값을 넣으면 메시지에 데이터가 담김
        response.setContentType("text/plain"); // content 헤더 정보에 데이터 들어감
        response.setCharacterEncoding("utf-8"); // 헤더 정보에 데이터 들어감
        response.getWriter().write("hello" + username); //getWriter는 바디에 데이터 들어감
    }
}
