package com.laide.LaideLibrary.repository;

import com.laide.LaideLibrary.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
//    @Query(value = "select * from books where book_id in " +
//            "(select book_id from rentals where returned=1)", nativeQuery = true)
//    List<Book> getAvailableBooks();
}