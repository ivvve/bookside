package org.dayside.book.web.book.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "rss")
public class NaverBookApiDetailResultModel {
    private Channel channel;

    @Data
    private static class Channel {
        private BookDetailModel item;
    }

    public BookDetailModel getBookDeatail() {
        return channel.item;
    }
}
