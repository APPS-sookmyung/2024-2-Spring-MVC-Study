package hello.servlet.basic.web.frontcontroller.v4;

import hello.servlet.basic.web.frontcontroller.MyView;
import hello.servlet.basic.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.basic.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.basic.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.taglibs.standard.lang.jstl.ImplicitObjects.createParamMap;

@WebServlet(name = "FrontControllerServiceV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServiceV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServiceV4() {
        controllerMap.put("/front-controller/v4/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>(); // 추가된 코드

        String viewName = controller.process(paramMap, model); // 모델 반환

        MyView view = viewResolver(viewName);
        view.render(model, request, response);
    }

    private Map<String, String>createParamMap(HttpServletRequest request) {
        Map<String, String> parmaMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName -> parmaMap.put(paramName, request.getParameter(paramName)));
        return parmaMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}

// paramMap만 넘어가는 것이 아니라 모델이 넘어가야 함
// 모델 뷰에서 모델을 꺼낼 일이 사라짐.
// 프레임워크에서 고친 코드는 별 없음

