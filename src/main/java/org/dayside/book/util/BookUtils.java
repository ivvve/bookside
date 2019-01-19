package org.dayside.book.util;

public class BookUtils {
    private BookUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String removeQueryStringFromUri(String uri) {
        return uri.split("\\?")[0];
    }

    public static String removeSubTitle(String title) {
        return title.split("\\(")[0];
    }

    public static String toDateFormat(String pubdate) {
        return new StringBuilder(pubdate.substring(0, 4))
                .append(".")
                .append(pubdate.substring(4, 6))
                .append(".")
                .append(pubdate.substring(6, 8))
                .toString();
    }
}
