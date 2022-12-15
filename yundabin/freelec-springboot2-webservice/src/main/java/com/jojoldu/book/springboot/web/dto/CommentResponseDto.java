package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.comments.Comments;
import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private Long id;
    private String comment;
    private Posts posts;
    private User user;
    private String userEmail;

    private LocalDateTime createdDate;

    public CommentResponseDto(Comments entity){
        this.id=entity.getId();
        this.comment=entity.getComment();
        this.posts=entity.getPosts();
        this.user=entity.getUser();
        this.userEmail=entity.getUser().getEmail();
        this.createdDate=entity.getCreatedDate();
    }
}