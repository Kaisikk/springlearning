package com.kaisikk.java.springlearning.config;

import com.kaisikk.java.springlearning.service.ScalaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource("classpath:my.properties")
public class AppConfig {

    @Value("${some}")
    public String configMonada;

    @Bean
    @Scope(value = "prototype")
    public ScalaService scalaService() {
        ScalaService scalaService = new ScalaService();
        scalaService.setMonada(configMonada);
        return scalaService;
    }

}
