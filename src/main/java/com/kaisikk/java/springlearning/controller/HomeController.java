package com.kaisikk.java.springlearning.controller;

import com.kaisikk.java.springlearning.model.Book;
import com.kaisikk.java.springlearning.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String getIndex(Model model) {

        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("newbook", new Book());

        return "index";
    }

    @PostMapping
    public String createBook(Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }
}
