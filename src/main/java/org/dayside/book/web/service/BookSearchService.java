package org.dayside.book.web.service;

import org.dayside.book.web.dto.CommonBookDto;
import org.springframework.data.domain.Page;

public interface BookSearchService {
    /**
     * API로부터 책 검색
     * @param keyword 검색어
     * @param target 검색 조건 (제목, ISBN ...)
     * @return
     */
    Page<CommonBookDto> searchBooks(String keyword, String target, int page);

    /**
     * API로부터 책 검색
     * @param keyword 검색어
     * @return
     */
    Page<CommonBookDto> searchBooks(String keyword, int page);
}
