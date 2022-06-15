//拦截器
package com.springboot.interceptor;

import com.springboot.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }
        String token = request.getHeader("token");//获取请求头的令牌
        try{
            JWTUtils.verify(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(600);
        }
        return false;
    }
}
