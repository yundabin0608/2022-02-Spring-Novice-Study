package com.jojoldu.book.springboot.domain.comments;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity

public class Comments extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable=false )
    private String comment; //댓글내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public Comments(String comment,Posts posts, User user){
        this.comment=comment;
        this.posts=posts;
        this.user=user;
    }

    public void update(String comment){
        this.comment=comment;
    }
}
