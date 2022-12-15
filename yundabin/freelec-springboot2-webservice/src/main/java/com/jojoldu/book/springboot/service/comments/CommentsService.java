package com.jojoldu.book.springboot.service.comments;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.domain.user.UserRepository;
import com.jojoldu.book.springboot.web.dto.*;
import com.jojoldu.book.springboot.domain.comments.Comments;
import com.jojoldu.book.springboot.domain.comments.CommentsRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;
    private final CommentsRepository commentsRepository;

    @Transactional
    public Long commentsSave(CommentRequestDto requestDto,Long postId,  String email){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        requestDto.setUser(optionalUser.get());

        Posts posts = postsRepository.findById(postId).orElseThrow(()->
                new IllegalArgumentException("댓글을 쓸 게시글이 존재하지 않습니다.")
        );
        requestDto.setPosts(posts);

        Comments comments = requestDto.toEntity();
        commentsRepository.save(comments);
        return comments.getId();
    }

    @Transactional
    public Long commentsUpdate(Long id, CommentRequestDto requestDto) {
        Comments comments = commentsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        comments.update(requestDto.getComment());
        return id;
    }

    @Transactional
    public void commentsDelete (Long id) {
        Comments comments = commentsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        commentsRepository.delete(comments);
    }

    @Transactional(readOnly = true)
    public List<CommentsListResponseDto> findMemDesc(String userEmail) {
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        return commentsRepository.findAllByUser(optionalUser).stream()
                .map(CommentsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
