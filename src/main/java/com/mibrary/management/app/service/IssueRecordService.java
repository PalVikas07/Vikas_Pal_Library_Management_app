package com.mibrary.management.app.service;

import com.mibrary.management.app.entity.Books;
import com.mibrary.management.app.entity.IssueRecord;
import com.mibrary.management.app.entity.User;
import com.mibrary.management.app.repository.BookRepository;
import com.mibrary.management.app.repository.IssueRecordRepository;
import com.mibrary.management.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IssueRecordService {

    @Autowired
    private IssueRecordRepository issueRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public IssueRecord issueTheBook(Long bookId) {
        Books book = bookRepository.findById(bookId)
                .orElseThrow(()-> new RuntimeException("Book Not Found"));

        if(book.getQuantity()<=0 || !book.getIsAvailable()){
            throw new RuntimeException("Book is not available");
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setIssueDate(LocalDate.now());
        issueRecord.setDueDate(LocalDate.now().plusDays(14));
        issueRecord.setIsReturned(false);
        issueRecord.setUser(user);
        issueRecord.setBook(book);

        book.setQuantity(book.getQuantity()-1);
        if (book.getQuantity()==0) {
            book.setAvailable(false);
        }

        bookRepository.save(book);
        return issueRecordRepository.save(issueRecord);
    }

    public IssueRecord returnTheBook(Long issueRecordId){
        IssueRecord issueRecord = issueRecordRepository.findById(issueRecordId)
                .orElseThrow(()-> new RuntimeException("Issue Record Not Found"));

        if(issueRecord.getIsReturned()){
            throw new RuntimeException("Book is already return");
        }
        Books books = issueRecord.getBook();
        books.setQuantity(books.getQuantity()+1);
        books.setAvailable(true);
        bookRepository.save(books);

        issueRecord.setReturnDate(LocalDate.now());
        issueRecord.setIsReturned(true);

        return issueRecordRepository.save(issueRecord);
    }
}
