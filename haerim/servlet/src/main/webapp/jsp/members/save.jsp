<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>

<%
    //request, reponse는 그냥 사용 가능. jsp도 결국은 servlet으로 바뀌기에 그 서비스 로직이 그대로 호출
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("memberSaveServlet.service");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title<title>
</head>
<body>
성공
<ul>
    <li>id=<%-member.getId()%></li>
    <li>username=<%member.getUsername()%></li>
    <li>sge<%=membet.getAge()%></li>
</ul>
<a href="/index.html">main</a>
</body>
</html>
