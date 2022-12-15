package com.jojoldu.book.springboot.web.dto;
import com.jojoldu.book.springboot.domain.comments.Comments;
import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {
    private String comment;
    private User user;
    private Posts posts;
    private Long postId;

    @Builder
    public CommentRequestDto(String comment, Posts posts, User user){
        this.comment=comment;
        this.posts=posts;
        this.postId=posts.getId();
        this.user=user;

    }
    public Comments toEntity(){
        return Comments.builder()
                .comment(comment)
                .posts(posts)
                .user(user)
                .build();

    }
}
