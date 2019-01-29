package org.dayside.book.web.book.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "rss")
public class NaverBookApiDetailResultDto {
    private Channel channel;

    @Data
    private static class Channel {
        private BookDetailDto item;
    }

    @JsonIgnore
    public BookDetailDto getBookDeatail() {
        return channel.item;
    }
}
