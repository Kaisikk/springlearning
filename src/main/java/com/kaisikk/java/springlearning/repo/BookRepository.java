package com.kaisikk.java.springlearning.repo;

import com.kaisikk.java.springlearning.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
