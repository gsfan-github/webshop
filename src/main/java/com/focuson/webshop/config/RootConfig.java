package com.focuson.webshop.config;

/**
 * @author Focuson created on 17-3-17.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;

@Order(0)
@Configuration
@ComponentScan(basePackages = {"com.focuson.webshop.bean",
        "com.focuson.webshop.service.product"})
@PropertySource({"classpath:webshop-${spring.profiles.active}.properties"})
public class RootConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
