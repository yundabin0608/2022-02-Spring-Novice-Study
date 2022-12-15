package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private User user;

    private String userEmail;

    private List<CommentResponseDto> comments;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.user=entity.getUser();
        this.userEmail=entity.getUser().getEmail();
        this.comments=entity
                .getComments()
                .stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
