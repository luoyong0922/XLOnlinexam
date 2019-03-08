package com.roy;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport{
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源映射到以下几个目录
        registry
                .addResourceHandler("/**")
//                .addResourceLocations("classpath:/META-INF/resources/")
//                .addResourceLocations("classpath:/resources/")
//                .addResourceLocations("classpath:/static/")
//                .addResourceLocations("classpath:/public/")
                .addResourceLocations("classpath:/templates/");

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        //加上以下代码，swagger-ui.html 才能正常显示
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}