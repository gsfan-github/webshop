package com.focuson.webshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Focuson created on 17-3-3.
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.focuson.webshop.config"})
@PropertySource("classpath:webshop-dev.properties")
public class WebShopApplication implements CommandLineRunner {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev");
        SpringApplication.run(WebShopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
