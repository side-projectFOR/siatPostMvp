package com.siat.post.domain.post;

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

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    
    @GetMapping("/{postIdx}")
    public @ResponseBody ResponseEntity<PostResponseDto> slectPost(@PathVariable long postIdx) throws Exception {
        PostResponseDto postInfo =postService.selectPost(postIdx);
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
    public @ResponseBody ResponseEntity<String> updatePost(@PathVariable long postIdx, @RequestBody PostUpdateRequestDto postUpdateRequest) throws Exception {
        int result=postService.updatePost(postIdx,postUpdateRequest);
        if(result>0){
            return ResponseEntity.ok().body("수정성공");

        }else{
            return ResponseEntity.badRequest().body("수정실패");
        }
    }
    @DeleteMapping("/{postIdx}")
    public @ResponseBody ResponseEntity<String> deletePost(@PathVariable long postIdx) throws Exception{
        int result=postService.updatePostDelete(postIdx, true);
        if(result>0){
            return ResponseEntity.ok().body("삭제성공");

        }else{
            return ResponseEntity.ok().body("삭제실패");
        }
    }
    
    
}
