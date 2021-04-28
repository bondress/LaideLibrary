package com.laide.LaideLibrary.controllers;

import com.laide.LaideLibrary.entities.Book;
import com.laide.LaideLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book createdBook = bookService.createBook(book);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBook.getBookId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") long id){
        Book book = bookService.getBook(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/allBooks")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> bookList = bookService.getAllBooks();
        return ResponseEntity.ok(bookList);
    }

//    @GetMapping("/availableBooks")
//    public ResponseEntity<List<Book>> getAvailableBooks(){
//        List<Book> bookList = bookService.getAvailableBooks();
//        return ResponseEntity.ok(bookList);
//    }

    @PutMapping("/updateBookById/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book){
        Book updateBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updateBook);
    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
