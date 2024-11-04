package hello.servlet.basic.response;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * http://localhost:8080/response-header
 *
 */
@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse
            response)
            throws ServletException, IOException {
        //[status-line]
        response.setStatus(HttpServletResponse.SC_OK); //200
        //[response-headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8"); // 한글 꺠짐 방지
        response.setHeader("Cache-Control", "no-cache, no-store, mustrevalidate"); //캐시 제거
        response.setHeader("Pragma", "no-cache"); // 캐시 제거하는 과거 버전
        response.setHeader("my-header","hello"); // 내가 원하는 임의의 헤더
        //[Header 편의 메서드]
        content(response);
        cookie(response);
        redirect(response);
        //[message body]
        PrintWriter writer = response.getWriter();
        writer.println("안녕하세요");
    }

    // Content 편의 메서드
    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    // 쿠키 편의 메서드
    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good"); // 쿠키라는 객체를 사용해서 더 편리하게 (윗 줄과 똑같은 효과)
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    // Redirect 편의 메서드
    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html"); // send 하나로 간결화
    }
}