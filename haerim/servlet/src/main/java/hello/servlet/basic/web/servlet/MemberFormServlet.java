package hello.servlet.basic.web.servlet;

import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    // private로 막아놨기 때문에 new를 쓸 수 없음

   // @Override 전 왜 이걸쓰면 오류가 날까요,,,
   protected void servlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //http 응답에 html이 나가도록 content body를 잡을거임
       response.setContentType("text/html");
       response.setCharacterEncoding("utf-8");

       PrintWriter w = response.getWriter();
       // w.write("<html>..."); // 내용이 쭉 나와야 함. // 자바코드로 html작성은 실상 무리
       w.write("<!DOCTYPE html>\n" +
               "<html>\n" +
               "<head>\n" +
               "    <meta charset=\"UTF-8\">\n" +
               "    <title>Title</title>\n" +
               "</head>\n" +
               "<body>\n" +
               "<form action=\"/servlet/members/save\" method=\"post\">\n" +
               "    username: <input type=\"text\" name=\"username\" />\n" +
               "    age:      <input type=\"text\" name=\"age\" />\n" +
               "    <button type=\"submit\">전송</button>\n" +
               "</form>\n" +
               "</body>\n" +
               "</html>\n");
    }
}
