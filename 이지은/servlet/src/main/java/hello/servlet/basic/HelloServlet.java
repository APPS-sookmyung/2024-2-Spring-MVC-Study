package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    /*
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        String age = request.getParameter("age");
        String studentId = request.getParameter("studentId");
        String major = request.getParameter("major");

        System.out.println("age = " + age);
        System.out.println("studentId = " + studentId);
        System.out.println("major = " + major);

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello" + username);

    }

     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String age = request.getParameter("age");

        System.out.println("age = " + age);
        response.setContentType("text/plain");
        response.getWriter().write("hello" + age);
    }
}
