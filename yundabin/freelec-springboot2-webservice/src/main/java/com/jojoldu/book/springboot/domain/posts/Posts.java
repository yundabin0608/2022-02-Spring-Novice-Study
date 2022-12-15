package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import com.jojoldu.book.springboot.domain.comments.Comments;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor // @Getter 와 @NoArgsConstructor(기본 생성자 자동 추가, public Post(){}의 효과) 는 롬복의 어노테이션으로 필수 아님
@Entity            // JPA의 어노테이션으로 필수라 클래스에 가까이 뒀음
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "posts", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Comments> comments;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성
    public Posts(String title, String content,User user){
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
