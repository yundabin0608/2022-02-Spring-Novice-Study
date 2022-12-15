package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.comments.Comments;
import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;


@Getter
public class CommentsListResponseDto {
    private String contents;
    private User user;
    private Posts posts;

    public CommentsListResponseDto(Comments entity){
        this.contents = entity.getComment();
        this.user = entity.getUser();
        this.posts = entity.getPosts();
    }
}