package com.estore.api.estoreapi;
import java.util.ArrayList;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.estore.api.estoreapi.Model.Product;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    ArrayList<Product> bookings = new ArrayList<Product>();
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
    }
    
}