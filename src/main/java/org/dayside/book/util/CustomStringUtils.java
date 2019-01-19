package org.dayside.book.util;

public class CustomStringUtils {
    private CustomStringUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String removeQueryStringFromUri(String uri) {
        return uri.split("\\?")[0];
    }
}
