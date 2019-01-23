package org.dayside.book.repository;

import org.dayside.book.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    public Optional<BookEntity> findByIsbn(String isbn);
}
