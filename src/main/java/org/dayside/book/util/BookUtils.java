package org.dayside.book.util;

import org.apache.commons.lang3.StringUtils;

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
        // api 검색 결과에서 비어 있는 결과가 있는 경우가 있다.
        if (StringUtils.isBlank(pubdate) || pubdate.length() != 8) {
            pubdate = "00000000";
        }

        return new StringBuilder(pubdate.substring(0, 4))
                .append(".")
                .append(pubdate.substring(4, 6))
                .append(".")
                .append(pubdate.substring(6, 8))
                .toString();
    }
}
