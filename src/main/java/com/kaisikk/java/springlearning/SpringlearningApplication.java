package com.kaisikk.java.springlearning;

import com.kaisikk.java.springlearning.model.Buyer;
import com.kaisikk.java.springlearning.repo.BuyerRepoJDBC;
import com.kaisikk.java.springlearning.service.CatSound;
import com.kaisikk.java.springlearning.service.ScalaService;
import com.kaisikk.java.springlearning.service.SoundAnimals;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import outside.Clojure;


@SpringBootApplication
@ComponentScan(basePackages = {"outside", "com.kaisikk.java.springlearning"})
@ImportResource("classpath:app-config.xml")
@EnableAsync
public class SpringlearningApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringlearningApplication.class, args);

        SoundAnimals soundAnimals = (SoundAnimals) ctx.getBean("horseSound");

        Clojure clojure = (Clojure) ctx.getBean("clojure");

        System.out.println(clojure.learnMe());

        System.out.println(soundAnimals.sound());

        ScalaService scalaService = (ScalaService) ctx.getBean("scalaService");

        System.out.println(scalaService.learnMe());

        BuyerRepoJDBC buyerRepoJDBC = ctx.getBean(BuyerRepoJDBC.class);

        buyerRepoJDBC.save(new Buyer(1L, "Kaisik", "Russia", 1000));
        buyerRepoJDBC.save(new Buyer(2L, "Ilya", "England", 2000));

        System.out.println(buyerRepoJDBC.findById("1"));
        System.out.println("-------------");
        buyerRepoJDBC.findAll().forEach(System.out::println);
    }


}
