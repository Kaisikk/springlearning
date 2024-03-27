package com.kaisikk.java.springlearning.controller;

import com.kaisikk.java.springlearning.gate.dto.UserDto;
import com.kaisikk.java.springlearning.gate.service.JsonPlaceHolderGate;
import com.kaisikk.java.springlearning.model.Book;
import com.kaisikk.java.springlearning.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = {"api"}, produces = "application/json")
public class HomeControllerRest {

    private BookRepository bookRepository;

    private JsonPlaceHolderGate jsonPlaceHolderGate;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setJsonPlaceHolderGate(JsonPlaceHolderGate jsonPlaceHolderGate) {
        this.jsonPlaceHolderGate = jsonPlaceHolderGate;
    }

    @GetMapping
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    public Book postBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("id") Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (Exception ex) {

        }
    }

    @PutMapping("/{id}")
    public Book putBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Book patchBook(@PathVariable("id") Long id, @RequestBody Book bookPath) {
        Book refreshBook = bookRepository.findById(id).get();

        if (bookPath.getAuthor() != null) {
            refreshBook.setAuthor(bookPath.getAuthor());
        }

        if (bookPath.getName() != null) {
            refreshBook.setName(bookPath.getName());
        }
        return bookRepository.save(refreshBook);
    }

    @GetMapping("/placeholder/test")
    public UserDto getResponseFromApi() {
        return jsonPlaceHolderGate.getUserListDto();
    }

}
