package com.example.upload.domain.member.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.upload.domain.member.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Optional<Member> findByUsername(String username);
    Optional<Member> findByApiKey(String apiKey);
}