package hello.servlet.basic.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "membetSaveServlet", urlPatterns = "/servlet/member/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    //save를 통해 받은 데이터를 읽을 줄 알아야 함
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("memberSaveServlet.service");
        // get의 쿼리 String에서 꺼내든 html post 방식이든 request.getParameter로 보내기 가능
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // 잘 받았는지 확인을 위한 html
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                "   <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "   <li>id="+member.getId()+"</li>\n" +
                "   <li>username="+member.getUsername()+"</li>\n" +
                "   <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
