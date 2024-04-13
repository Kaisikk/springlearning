package com.kaisikk.java.springlearning.config;

import com.kaisikk.java.springlearning.service.ScalaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;

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

    /**
     * Объект для отправки и получения Rest запросов
     *
     * @return
     */
    @Bean
    @Scope("prototype")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Экзекутор для запуска тасков в приложении (его конфиг)
     *
     * @return
     */
    @Bean
    public Executor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(500);
        return executor;
    }

}
