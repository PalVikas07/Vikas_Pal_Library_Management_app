package com.mibrary.management.app.repository;

import com.mibrary.management.app.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Books, Long> {
}
