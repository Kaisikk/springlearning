package com.kaisikk.java.springlearning.controller;

import com.kaisikk.java.springlearning.model.Book;
import com.kaisikk.java.springlearning.repo.BookRepository;
import com.kaisikk.java.springlearning.service.SoundAnimals;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private BookRepository bookRepository;

    private SoundAnimals soundAnimals;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setSoundAnimals(SoundAnimals soundAnimals) {
        this.soundAnimals = soundAnimals;
    }

    @GetMapping("/")
    public String getIndex(Model model) {

        System.out.println(soundAnimals.sound());

        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("newbook", new Book());

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
