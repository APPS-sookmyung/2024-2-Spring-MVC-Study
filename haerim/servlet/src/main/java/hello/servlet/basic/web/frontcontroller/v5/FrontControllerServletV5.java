package hello.servlet.basic.web.frontcontroller.v5;

import hello.servlet.basic.web.frontcontroller.ModelView;
import hello.servlet.basic.web.frontcontroller.MyView;
import hello.servlet.basic.web.frontcontroller.v3.ControllerV3;
import hello.servlet.basic.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.basic.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.basic.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.basic.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.basic.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.basic.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.basic.web.frontcontroller.v5.adapter.ControllerV3HandlerAfdapter;
import hello.servlet.basic.web.frontcontroller.v5.adapter.ControllerV4HandlerAfdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FrontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMaappingMap = new HashMap<>();
    // 기존과 차이는 특정 컨트롤러가 아닌 다 지원이 가능하여 Object 변수
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        // 뒤에 v3 고쳐야 하나 싶지만 자바에서 최상위엔 무조건 object이니 굳이 바꾸지 않아도 object가 담길 것.
        initHandlerMappingMap();

        initHandlerAdapter();
    }

    private void initHandlerMappingMap() {
        handlerMaappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMaappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMaappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        //V4 추가
        handlerMaappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMaappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMaappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapter() {
        handlerAdapters.add(new ControllerV3HandlerAfdapter());
        handlerAdapters.add(new ControllerV4HandlerAfdapter()); //V4 추가
    }

    // 위 코드로는 초기화까진 완료 (데이터를 다 가지고 있음)

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //핸들러 호출 (getHandler)
        //MemberFormControllerV3 호출될 것임.
        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = gethandlerAdapter(handler);

        // (handle 호출)
        ModelView mv = adapter.handle(request, response, handler);

        // view 얻은 다음 호출
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMaappingMap.get(requestURI);
    }

    private MyHandlerAdapter gethandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
