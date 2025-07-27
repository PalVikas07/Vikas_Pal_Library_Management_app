package com.mibrary.management.app.service;

import com.mibrary.management.app.dto.BookDTO;
import com.mibrary.management.app.entity.Books;
import com.mibrary.management.app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Books> getAllBooks() {
        return bookRepository.findAll() ;
    }

    public Books getBookById(Long id) {
        Books book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Not Found"));
        return book;
    }

    public Books addBook(BookDTO bookDTO) {
        Books books = new Books();
        books.setTitle(bookDTO.getTitle());
        books.setAuthor(bookDTO.getAuthor());
        books.setIsbn(bookDTO.getIsbn());
     //   books.setAvailable(bookDTO.getIsAvailable());
        books.setQuantity(bookDTO.getQuantity());
        return bookRepository.save(books);
    }

    public Books updateBook(Long id, BookDTO bookDTO) {
        Books oldBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Not Found"));
        oldBook.setTitle(bookDTO.getTitle());
        oldBook.setAuthor(bookDTO.getAuthor());
        oldBook.setIsbn(bookDTO.getIsbn());
        oldBook.setQuantity(bookDTO.getQuantity());
        return bookRepository.save(oldBook);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
