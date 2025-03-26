package com.example.upload.domain.post.post.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.upload.domain.member.member.entity.Member;
import com.example.upload.domain.post.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>, CustomPostRepository{
    Optional<Post> findTopByOrderByIdDesc();
    Optional<Post> findTop1ByAuthorAndPublishedAndTitleOrderByIdDesc(Member author, boolean published, String title);
}
