package mingu.inflearn.framework.web;

import lombok.extern.slf4j.Slf4j;
import mingu.inflearn.framework.data.MySQLPageRequest;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class MySQLPageRequestHandleMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String DEFAULT_PARAMETER_PAGE = "page";
    private static final String DEFAULT_PARAMETER_SIZE = "size";
    private static final int DEFAULT_SIZE = 20;

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        // 현재 페이지
        int page = NumberUtils.toInt(request.getParameter(DEFAULT_PARAMETER_PAGE), 1);
        // 리스트 갯수
        int offset = NumberUtils.toInt(request.getParameter(DEFAULT_PARAMETER_SIZE), DEFAULT_SIZE);
        // 시작지점
        int limit = (offset * page) - offset;
        log.info("page : {}", page);
        log.info("limit : {}, offset : {}", limit, offset);
        return new MySQLPageRequest(page, offset, limit, offset);
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return MySQLPageRequest.class.isAssignableFrom(methodParameter.getParameterType());
    }
}
