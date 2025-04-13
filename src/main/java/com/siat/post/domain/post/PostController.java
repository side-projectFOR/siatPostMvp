package com.siat.post.domain.post;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siat.post.domain.post.dto.PostRequestDto;
import com.siat.post.domain.post.dto.PostResponseDto;
import com.siat.post.domain.post.dto.PostUpdateRequestDto;

import ch.qos.logback.core.util.StringUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
@RequiredArgsConstructor
@RequestMapping("/{boardSlug}/posts")
public class PostController {
    private final PostService postService;
    
    // @GetMapping("")
    // public @ResponseBody ResponseEntity<List<PostResponseDto>> selectPosts() throws Exception {
    //     List<PostResponseDto> postList = postService.s();

    //     if (postList != null) {
    //         return ResponseEntity.ok().body(postList);

    //     } else {
    //         return ResponseEntity.badRequest().build();
    //     }
    // }
    @GetMapping
    public @ResponseBody ResponseEntity<List<PostResponseDto>> selectPostsByBoard(@PathVariable String boardSlug) throws Exception {
        if (StringUtil.isNullOrEmpty(boardSlug)) {
            return ResponseEntity.badRequest().build();
        }
        List<PostResponseDto> postList = postService.selectPostsByBoard(boardSlug);

        if (postList != null) {
            return ResponseEntity.ok().body(postList);

        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{postIdx}")
    public @ResponseBody ResponseEntity<PostResponseDto> selectPost(@PathVariable Long postIdx) throws Exception {
        PostResponseDto postInfo = postService.selectPost(postIdx);
        
        if(postInfo!=null){
            return ResponseEntity.ok().body(postInfo);

        }else{
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping
    public @ResponseBody ResponseEntity<String> insertPost(@RequestBody PostRequestDto post) throws Exception {
        int result=postService.insertPost(post);
        if(result>0){
            return ResponseEntity.ok().body("작성성공");

        }else{
            return ResponseEntity.badRequest().body("작성실패");
        }
    }
    @PutMapping("/{postIdx}")
    public @ResponseBody ResponseEntity<String> updatePost(@PathVariable Long postIdx, @RequestBody PostUpdateRequestDto postUpdateRequest) throws Exception {
        int result=postService.updatePost(postIdx,postUpdateRequest);
        if(result>0){
            return ResponseEntity.ok().body("수정성공");

        }else{
            return ResponseEntity.badRequest().body("수정실패");
        }
    }
    @DeleteMapping("/{postIdx}")
    public @ResponseBody ResponseEntity<String> deletePost(@PathVariable Long postIdx) throws Exception {
        int result=postService.softDeltePost(postIdx);
        if(result>0){
            return ResponseEntity.ok().body("삭제성공");

        }else{
            return ResponseEntity.ok().body("삭제실패");
        }
    }
    
    
}
