package hello.servlet.basic;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
class Hello {
    private String username;
    private int age;
    private long studentId;
    private String major;

    public Hello(String username, int age, long studentId, String major) {
        this.username = username;
        this.age = age;
        this.studentId = studentId;
        this.major = major;
    }
}
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // super.service(req, resp); // 서블릿이 호출되면 이 서비스 로직이 호출됨
        System.out.println("HelloServlet.service");
//        System.out.println("request = " + request);
//        System.out.println("request = " + response);

          String username = request.getParameter("username"); // 쿼리 파라미터를 쉽게 읽도록
//        System.out.println("username = " + username);
//
          String age = request.getParameter("age"); // 쿼리 파라미터 확장 age(나이)
//        System.out.println("age = " + age);
//
          String studentId = request.getParameter("studentId"); // 쿼리 파라미터 확장 studentId(학번)
//        System.out.println("studentId = " + studentId);
//
          String major = request.getParameter("major"); // 쿼리 파라미터 확장 major(전공)
//        System.out.println("major = " + major);

        Hello hello = new Hello("Lee", 21, 2313638, "computer");
        String helloJsonString = this.gson.toJson(hello);
        // 응답 메시지 생성 (HttpServletResponse) 값을 넣으면 메시지에 데이터가 담김
        response.setContentType("application/json"); // content 헤더 정보에 데이터 들어감
        response.setCharacterEncoding("utf-8"); // 헤더 정보에 데이터 들어감
        PrintWriter out = response.getWriter();
        out.print(helloJsonString);
        //localhost:8082/hello?username=Lee&age=21&studentId=2313638&major=computer
    }
}
