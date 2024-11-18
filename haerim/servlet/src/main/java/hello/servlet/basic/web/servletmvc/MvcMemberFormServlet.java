package hello.servlet.basic.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

@WebServlet(name = "MvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    // 고객 요청이 오면 이 메소드가 호출됨.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); //컨트롤러에서 뷰로 이동할 때 사용하는 것
        // viewPath 경로를 다시 호출
        /**
         * 서버끼리 내부에서 호출.
         * 제어권을 넘겨주는 것.
         */
        dispatcher.forward(request, response); //JSP를 찾아가서 넘어가서 호출


    }
}
