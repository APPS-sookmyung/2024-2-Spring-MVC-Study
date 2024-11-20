package hello.servlet.basic.web.frontcontroller.v4.controller;

import hello.servlet.basic.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form"; // 이러면 끝, 모델 뷰가 아예 필요 없음.
    }
}

// V3랑 비교해보면 모델은 프론트 컨트롤러에서 만들어주기에 여기에 필요한 값만 담아서 넘겨주면 됨.
