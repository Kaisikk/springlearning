package com.kaisikk.java.springlearning;

import com.kaisikk.java.springlearning.service.CatSound;
import com.kaisikk.java.springlearning.service.SoundAnimals;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringlearningApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringlearningApplication.class, args);

        SoundAnimals soundAnimals = (SoundAnimals) ctx.getBean("horseSound");

        System.out.println(soundAnimals.sound());
    }

}
