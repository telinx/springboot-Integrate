package com.mohai.one.app.core.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当访问接口没有权限时，返回403，拒绝访问
 * 该类将不生效，AccessDeniedException异常会被自定义的全局异常处理类拦截到。
 * @Auther: moerhai@qq.com
 * @Date: 2020/11/25 01:02
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAccessDeniedHandler.class);
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        LOGGER.debug("‘{}’请求已授权但无访问权限",request.getRequestURL());
        //当用户在没有授权的情况下访问受保护的REST资源时，将调用此方法返回403 Forbidden响应。
        response.sendError(HttpServletResponse.SC_FORBIDDEN,
                accessDeniedException.getMessage());
    }
}
