package org.dayside.book.repository;

import org.dayside.book.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    public Optional<MemberEntity> findByEmailAndPassword(String email, String password);
}