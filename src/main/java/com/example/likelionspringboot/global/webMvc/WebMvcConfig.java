package com.example.likelionspringboot.global.webMvc;

import com.example.likelionspringboot.global.interceptor.NeedToAdminInterceptor;
import com.example.likelionspringboot.global.interceptor.NeedToLoginInterceptor;
import com.example.likelionspringboot.global.interceptor.NeedToLogoutInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final NeedToLogoutInterceptor needToLogoutInterceptor;
    private final NeedToLoginInterceptor needToLoginInterceptor;
    private final NeedToAdminInterceptor needToAdminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(needToLogoutInterceptor)
                .addPathPatterns("/member/login")
                .addPathPatterns("/member/join")
                .addPathPatterns("/member/findUsername")
                .addPathPatterns("/member/findPassword");
        registry.addInterceptor(needToLoginInterceptor)
                .addPathPatterns("/admin/**")
                .addPathPatterns("/article/write")
                .addPathPatterns("/article/modify/**")
                .addPathPatterns("/article/delete/**");
        registry.addInterceptor(needToAdminInterceptor)
                .addPathPatterns("/admin/**");
    }
}
