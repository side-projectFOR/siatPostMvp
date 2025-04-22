package com.siat.post.domain.post;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.siat.post.domain.post.dto.PostRequestDto;
import com.siat.post.domain.post.dto.PostResponseDto;
import com.siat.post.domain.post.dto.PostSecretRequestDto;
import com.siat.post.domain.post.dto.PostSimpleInfoResponseDto;
import com.siat.post.domain.post.dto.PostUpdateRequestDto;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequiredArgsConstructor
@RequestMapping("/{boardSlug}/posts")
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostSimpleInfoResponseDto>> selectPostsByBoardSlug(@PathVariable String boardSlug) throws Exception {
        if (StringUtils.hasText(boardSlug)) {
            return ResponseEntity.badRequest().build();
        }
        List<PostSimpleInfoResponseDto> postList = postService.selectPostsByBoard(boardSlug);

        if (postList != null) {
            return ResponseEntity.ok().body(postList);

        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{postIdx}")
    public ResponseEntity<? super PostResponseDto> selectPost(@PathVariable String boardSlug,@PathVariable Long postIdx) throws Exception {
        if(postService.isPostSecret(postIdx)){
            return ResponseEntity.ok().body("비밀글입니다");
        }
        PostResponseDto postInfo = postService.selectPost(postIdx);
        
        if(postInfo!=null){
            return ResponseEntity.ok().body(postInfo);

        }else{
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/{postIdx}/auth")
    public ResponseEntity<PostResponseDto> selectPostBySecret(@PathVariable String boardSlug,@PathVariable Long postIdx, @RequestBody PostSecretRequestDto request) throws Exception {
        if(postIdx==null||request==null){
            return ResponseEntity.badRequest().build();
        }
        PostResponseDto postInfo = postService.selectPostWithPassword(request);
        
        if(postInfo!=null){
            return ResponseEntity.ok().body(postInfo);

        }else{
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<String> insertPost(@PathVariable String boardSlug,@RequestBody PostRequestDto post) throws Exception {
        int result=postService.insertPost(post);
        if(result>0){
            return ResponseEntity.ok().body("작성성공");

        }else{
            return ResponseEntity.badRequest().body("작성실패");
        }
    }
    @PutMapping("/{postIdx}")
    public ResponseEntity<String> updatePost(@PathVariable String boardSlug,@PathVariable Long postIdx, @RequestBody PostUpdateRequestDto postUpdateRequest) throws Exception {
        int result=postService.updatePost(postIdx,postUpdateRequest);
        if(result>0){
            return ResponseEntity.ok().body("수정성공");

        }else{
            return ResponseEntity.badRequest().body("수정실패");
        }
    }
    @DeleteMapping("/{postIdx}")
    public ResponseEntity<String> deletePost(@PathVariable String boardSlug,@PathVariable Long postIdx) throws Exception {
        int result=postService.softDeltePost(postIdx);
        if(result>0){
            return ResponseEntity.ok().body("삭제성공");

        }else{
            return ResponseEntity.ok().body("삭제실패");
        }
    }
    
    
}
