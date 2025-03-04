package com.spring.interceptor.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    // Pre-handle: Before the controller method is invoked
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader  = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: No token provided");
            return false;
        }

        String token = authHeader.substring(7);  // Extract token from 'Bearer <token>'

        if("valid-token".equals(token.trim())){
            return true;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Unauthorized: Invalid token");
        return false;
    }

    // Post-handle: After the controller method is invoked but before sending the response

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Example: Log response information after controller execution
        System.out.println("Post Handle: After controller execution but before sending the response.");
        // We can also add custom headers or modify the modelAndView if needed
    }

    // After-completion: After the complete request is finished and the response is sent

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Example: Log request and response after completion
        System.out.println("After Completion: Request and response completed.");
        System.out.println("Request: "+request.getRequestURI());
        System.out.println("Response: "+response.getCharacterEncoding());
        if(ex != null){
            System.out.println("Exception: "+ex.getMessage());
        }
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
