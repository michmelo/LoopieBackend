package com.example.loopie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String frontend = System.getenv("FRONTEND_URL");
                if (frontend == null || frontend.isEmpty()) {
                    frontend = "loopie-fs2.s3-website-us-east-1.amazonaws.com";
                }

                String originHttp = frontend.startsWith("http") ? frontend : "http://" + frontend;
                String originHttps = frontend.startsWith("http")
                    ? (frontend.startsWith("https") ? frontend : frontend.replaceFirst("^http://", "https://"))
                    : "https://" + frontend;

                registry.addMapping("/**")
                    .allowedOriginPatterns(originHttp, originHttps)
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);
            }
        };
    }
}
