package org.dayside.book.web.book.model;

import lombok.Data;
import org.dayside.book.util.CustomStringUtils;

@Data
public class BookSearchModel {
    private String title;
    private String image;
    private String publisher;
    private String author;
    private int price;
    private int discount;
    private String pubdate;
    private String isbn;

    /**
     * api 검색 결과에서 image(책의 표지 이미지 주소)는 query string을 붙여서 제공하는데
     * 이를 그대로 사용하면 책의 표지 이미지가 너무 작기 때문에
     * query string을 제거한 image url로 지정한다.
     * @param image
     */
    public void setImage(String image) {
        this.image = CustomStringUtils.removeQueryStringFromUri(image);
    }
}
