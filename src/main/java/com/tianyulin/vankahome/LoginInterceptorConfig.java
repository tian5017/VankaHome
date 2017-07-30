package com.tianyulin.vankahome;

import com.tianyulin.vankahome.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by tianyulin on 2017/5/27.
 */
@Configuration
public class LoginInterceptorConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(new LoginInterceptor());
        //配置拦截路径
        ir.addPathPatterns("/article/index");
        ir.addPathPatterns("/article/save");
    }
}
