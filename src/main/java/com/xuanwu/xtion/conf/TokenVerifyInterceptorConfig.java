package com.xuanwu.xtion.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 拦截路径配置
 * @author Administrator
 *
 */
@Configuration
public class TokenVerifyInterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenVerifyInterceptor()).addPathPatterns("/#**");
        super.addInterceptors(registry);
    }
}