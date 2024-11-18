package hello.servlet.basic.web.frontcontroller.v4.controller;

import hello.servlet.basic.web.frontcontroller.v4.ControllerV4;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.put("member", member);
        return "save-result";
    }
}

// 모델 뷰 만들고 put 하는 과정 모두 생략 가능
// 파라미터에서 값 꺼내고 비즈니스 로직 실행한 뒤 모델에다가는 값 put해주면 됨
