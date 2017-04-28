package com.focuson.webshop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Focuson created on 17-3-16.
 */

@Configuration
@ComponentScan("com.focuson.webshop.controller")
//@EnableWebMvc //http://stackoverflow.com/questions/27381781/java-spring-boot-how-to-map-my-app-root-to-index-html/27383522#27383522
@Import(RootConfig.class)
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**", "/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        //注意forward 和 redirect在此处的区别
        registry.addViewController("/systemManage").setViewName("forward:system_manage.html");
//        registry.addViewController("/systemManage").setViewName("system_manage");
    }
}
