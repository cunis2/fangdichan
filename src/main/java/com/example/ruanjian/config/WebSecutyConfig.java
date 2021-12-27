package com.example.ruanjian.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebSecutyConfig implements WebMvcConfigurer {
    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {//拦截器

        registry.addInterceptor(getSecurityInterceptor())
                .addPathPatterns("/index")//拦截全部
                .excludePathPatterns("/login**");
    }

    private static class SecurityInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws IOException {
            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect("/login");
                return false;
            }
            return true;
        }
    }
}

