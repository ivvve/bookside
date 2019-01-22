package org.dayside.book.repository;

import org.dayside.book.domain.BookOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrderEntity, Long> {
}
