package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    // SpringDataJpa에서 제공하지 않는 메소드는 @Query로 작성해도 되고(가독성이 더 좋음) 제공하는 기본 메소드만으로도 해결 가능
    List<Posts> findAllDesc();

    // 유저 아이디에 해당하는 값과 posts의 user_id 일치하는 게시글들만 찾기
    // user_id로 찾으면 안대 생각과는 다르게 쿼리가 날라가거든
    List<Posts> findAllByUser(Optional<User> user);
}
