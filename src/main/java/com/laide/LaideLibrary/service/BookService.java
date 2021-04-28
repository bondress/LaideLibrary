package com.laide.LaideLibrary.service;

import com.laide.LaideLibrary.entities.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    Book createBook(Book book);
    Book getBook(long id);
    List<Book> getAllBooks();
    //List<Book> getAvailableBooks();
    Book updateBook(long id, Book book);
    void deleteBook(long id);
}
