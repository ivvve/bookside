package org.dayside.book;

import org.dayside.book.domain.MemberEntity;
import org.dayside.book.domain.enums.MemberStatus;
import org.dayside.book.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(MemberRepository memberRepository) {
        return arg -> memberRepository.save(MemberEntity.builder()
                .email("sonyc5720@gmail.com")
                .password("hello1234")
                .memberStatus(MemberStatus.ADMIN)
                .build());
    }
}

