package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.comments.CommentsService;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import com.jojoldu.book.springboot.web.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class CommentsApiController {
    private final CommentsService commentsService;


    @PostMapping("/posts/detail/{id}/comments")
    public Long commentSave(@RequestBody CommentRequestDto requestDto,@PathVariable Long id, @LoginUser SessionUser sessionUser){

        return commentsService.commentsSave(requestDto, id, sessionUser.getEmail());
    }

    @PutMapping("/posts/detail/{postId}/comments/{id}")
    public Long commentUpdate(@PathVariable Long id, @RequestBody CommentRequestDto requestDto){
        return commentsService.commentsUpdate(id, requestDto);
    }

    @DeleteMapping("/posts/detail/{postId}/comments/{id}")
    public Long commentDelete(@PathVariable Long id){
        commentsService.commentsDelete(id);
        return id;
    }

}
