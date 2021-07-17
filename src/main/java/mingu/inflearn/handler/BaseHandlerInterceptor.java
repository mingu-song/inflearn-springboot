package mingu.inflearn.handler;

import lombok.extern.slf4j.Slf4j;
import mingu.inflearn.config.exception.BaseException;
import mingu.inflearn.config.http.BaseResponseCode;
import mingu.inflearn.framework.web.bind.annotation.RequestConfig;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class BaseHandlerInterceptor implements AsyncHandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle requestURI : {}", request.getRequestURI());
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            log.info("handler method : {}", handlerMethod);
            RequestConfig requestConfig = handlerMethod.getMethodAnnotation(RequestConfig.class);
            if (requestConfig != null ) {
                if (requestConfig.loginCheck()) {
                    throw new BaseException(BaseResponseCode.LOGIN_REQUIRED);
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle requestURI : {}", request.getRequestURI());
    }
}
