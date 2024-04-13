package com.kaisikk.java.springlearning.controller;

import com.kaisikk.java.springlearning.gate.dto.UserDto;
import com.kaisikk.java.springlearning.gate.service.JsonPlaceHolderGate;
import com.kaisikk.java.springlearning.model.Book;
import com.kaisikk.java.springlearning.repo.BookRepository;
import com.kaisikk.java.springlearning.service.SoundAnimals;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Controller
@Slf4j
public class HomeController {

    private BookRepository bookRepository;

    private SoundAnimals soundAnimals;

    private JsonPlaceHolderGate jsonPlaceHolderGate;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setSoundAnimals(SoundAnimals soundAnimals) {
        this.soundAnimals = soundAnimals;
    }

    @Autowired
    public void setJsonPlaceHolderGate(JsonPlaceHolderGate jsonPlaceHolderGate) {
        this.jsonPlaceHolderGate = jsonPlaceHolderGate;
    }

    /**
     * Отрисовка основной страницы
     *
     * @param model
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws TimeoutException
     */
    @GetMapping("/")
    public String getIndex(Model model) throws ExecutionException, InterruptedException, TimeoutException {

        System.out.println(soundAnimals.sound());


        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("newbook", new Book());
//        model.addAttribute("api", userDto.getBody());

        try {
            UserDto userDto = jsonPlaceHolderGate.getUserListDtoAsync().get(5, TimeUnit.SECONDS);

            model.addAttribute("api", userDto.getBody());
        } catch (Exception ex) {
            log.info("too long");
        }
        return "index";
    }

    @PostMapping
    public String createBook(Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("/{id}/show")
    public String showById(@PathVariable("id") Long id, Model model) {
        if (bookRepository.findById(id).isPresent()) {
            model.addAttribute("book", bookRepository.findById(id).get());
            return "show";
        }
        return "show";
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {

        bookRepository.deleteById(id);
        return "redirect:/";
    }


    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        if (bookRepository.findById(id).isPresent()) {
            model.addAttribute("book", bookRepository.findById(id).get());
            return "edit";
        }
        model.addAttribute("book", new Book());
        return "edit";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid Book book, Errors errors) {
        if (errors.hasErrors()) {
            return "edit";
        }
        bookRepository.save(book);
        return "redirect:/";
    }


}
