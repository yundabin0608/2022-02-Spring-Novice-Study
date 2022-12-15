package com.jojoldu.book.springboot.domain.comments;

import com.jojoldu.book.springboot.domain.comments.Comments;
import com.jojoldu.book.springboot.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public interface CommentsRepository extends JpaRepository<Comments,Long>{
    List<Comments> findAllByUser(Optional<User> optionalUser);
}

