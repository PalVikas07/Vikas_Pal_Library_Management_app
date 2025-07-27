package com.mibrary.management.app.controller;

import com.mibrary.management.app.dto.BookDTO;
import com.mibrary.management.app.entity.Books;
import com.mibrary.management.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getallbooks")
    public ResponseEntity<List<Books>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/getbookbyid/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("/addbook")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public ResponseEntity<Books> addBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }

    @PutMapping("/updatebook/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public ResponseEntity<Books> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
    }

    @DeleteMapping("/deletebook/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }

}
