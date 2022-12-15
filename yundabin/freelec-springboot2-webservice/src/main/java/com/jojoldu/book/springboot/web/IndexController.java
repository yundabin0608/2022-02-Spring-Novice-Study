package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.comments.CommentsService;
import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.CommentResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final CommentsService commentsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
        // 머스테치 스타터 덕분에 index 반환시, 앞의 경로와 뒤의 파일 확장자 즉 src/main/recources/template/index.mustache로 전환후 뷰 리졸버가 처리
        // Model 에는 서버템플릿 엔진에서 쓸 객체 저장 -> index.mustache로 전달
    }

    // 글 등록 링크 클릭시 이동
    @GetMapping("/posts/save")
    public String postsSave(@LoginUser SessionUser sessionUser, Model model) {
        if(sessionUser!=null){
            model.addAttribute("userEmail",sessionUser.getEmail());
        }
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
    @GetMapping("/posts/detail/{id}") // 이곳에 sessionUser가 필요한것인가
    public String getDetail(@PathVariable Long id, @LoginUser SessionUser sessionUser, Model model){
        PostsResponseDto dto = postsService.findById(id);

        List<CommentResponseDto> comments = dto.getComments();

        if(comments !=null && !comments.isEmpty()){
            model.addAttribute("comments",comments);
        }
        model.addAttribute("post",dto);
        return "posts-detail";
    }
    @GetMapping("/mypage")
    public String mypage(Model model, @LoginUser SessionUser user){
        model.addAttribute("userName", user.getName());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userPicture", user.getPicture());
        return "mypage";
    }

    @GetMapping("/myposts")
    public String myposts(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findMemDesc(user.getEmail()));
        return "myposts";
    }

    @GetMapping("/mycomments")
    public String mycomments(Model model, @LoginUser SessionUser user) {
        model.addAttribute("comments", commentsService.findMemDesc(user.getEmail()));
        return "mycomments";
    }
}