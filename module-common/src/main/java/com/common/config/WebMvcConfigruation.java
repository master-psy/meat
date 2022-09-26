package com.common.config;

import com.common.interceptor.DecryptInterceptor;
import com.common.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * mvc config
 *
 * @author Administrator
 * @Desc
 */
@Configuration
public class WebMvcConfigruation implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private DecryptInterceptor decryptInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/login/sendSmsCode")
                .excludePathPatterns("/login/doLogin")
                .excludePathPatterns("/food/getFoodList");
        //.excludePathPatterns("/static/**");
        registry.addInterceptor(decryptInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/error");
        //.excludePathPatterns("/static/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * 设置相应字符编码UTF-8
     */
    @Bean
    public HttpMessageConverter<String> stringResponseBodyConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    /**
     * 跨域支持
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许跨域访问的路径
                .allowedOriginPatterns("*")    // 允许跨域访问的源allowedOrigins=>allowedOriginPatterns
                .allowedMethods("POST", "GET", "OPTIONS", "DELETE")  // 允许请求方法
                .maxAge(3600)     // 预检间隔时间
                .allowCredentials(true)     // 是否发送cookie
                .allowedHeaders("Content-Type", "x-requested-with", "X-USERID", "X-TOKEN", "origin");   //允许请求头携带的key
    }

    @Bean
    public HttpMessageConverter jsonResponseBodyConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(stringResponseBodyConverter());
        converters.add(jsonResponseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    ///**
    // * MVC设置默认首页
    // *
    // * @param registry
    // */
    //@Override
    //public void addViewControllers(ViewControllerRegistry registry) {
    //    registry.addViewController("/").setViewName("index");
    //    registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    //    WebMvcConfigurer.super.addViewControllers(registry);
    //}
}
