package com.laide.LaideLibrary.service.serviceImpl;

import com.laide.LaideLibrary.entities.Book;
import com.laide.LaideLibrary.exception.ResourceNotFoundException;
import com.laide.LaideLibrary.repository.BookRepository;
import com.laide.LaideLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {

        return bookRepository.save(book);
    }

    @Override
    public Book getBook(long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(!optionalBook.isPresent()){
            throw new ResourceNotFoundException("Book with id " + id + " is not found");
        }
        return optionalBook.get();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

//    @Override
//    public List<Book> getAvailableBooks() {
//        return bookRepository.getAvailableBooks();
//    }

    @Override
    public Book updateBook(long id, Book book) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(!optionalBook.isPresent()){
            throw new ResourceNotFoundException("Book with id " + id + " is not found");
        }
        book.setBookId(id);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(!optionalBook.isPresent()){
            throw new ResourceNotFoundException("Book with id " + id + " is not found");
        }
        bookRepository.deleteById(id);
    }
}
