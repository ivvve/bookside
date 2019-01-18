package org.dayside.book.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "naver.book")
public class NaverBookApiProperties {
    private String clientId;
    private String clientSecret;
}
