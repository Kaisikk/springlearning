package com.kaisikk.java.springlearning;

import com.kaisikk.java.springlearning.service.CatSound;
import com.kaisikk.java.springlearning.service.ScalaService;
import com.kaisikk.java.springlearning.service.SoundAnimals;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import outside.Clojure;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {"outside", "com.kaisikk.java.springlearning"})
@ImportResource("classpath:app-config.xml")
public class SpringlearningApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringlearningApplication.class, args);

        SoundAnimals soundAnimals = (SoundAnimals) ctx.getBean("horseSound");

        Clojure clojure = (Clojure) ctx.getBean("clojure");

        System.out.println(clojure.learnMe());

        System.out.println(soundAnimals.sound());

        ScalaService scalaService = (ScalaService) ctx.getBean("scalaService");

        System.out.println(scalaService.learnMe());
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//
//        return args -> {
//            System.out.println("All beans");
//            Arrays.stream(ctx.getBeanDefinitionNames()).sorted().forEach(System.out::println);
//        };
//
//    }

}
