package com.siat.post.domain.post;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{boardSlug}/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "PostController", description = "게시판별 게시글CRUD 및 비밀글 인증/조회 API")
public class PostController {
    private final PostService postService;

    @Operation(
        summary = "게시판별 게시글 목록 조회",
        description = "특정 게시판의 모든 게시글 목록을 조회합니다."
    )
    @ApiResponse(responseCode = "200", description = "게시글 목록 조회 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    @GetMapping
    public ResponseEntity<List<PostSimpleInfoResponseDto>> selectPostsByBoardSlug(
        @Parameter(description = "게시판 식별자(boardSlug)", example = "free") @PathVariable String boardSlug
    ) throws Exception {
        if (!StringUtils.hasText(boardSlug)) {
            return ResponseEntity.badRequest().build();
        }
        List<PostSimpleInfoResponseDto> postList = postService.selectPostsByBoardSlug(boardSlug);

        if (postList != null) {
            return ResponseEntity.ok().body(postList);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
        summary = "게시글 상세 조회",
        description = "게시판 내 특정 게시글의 상세 정보를 조회합니다. 비밀글일 경우 안내 메시지를 반환합니다."
    )
    @ApiResponse(responseCode = "200", 
            description = "게시글 조회 성공", 
            content = @Content(
                mediaType = "application/json",
                    schema = @Schema(implementation = PostResponseDto.class
                    )
            )
    )
    @ApiResponse(responseCode = "400", description = "잘못된 요청 또는 게시글 없음")
    @ApiResponse(responseCode = "403", description = "비밀글이므로 /{postIdx}/auth로 요청필요")
    @GetMapping("/{postIdx}")
    public ResponseEntity<PostResponseDto> selectPost(
        @Parameter(description = "게시판 식별자(boardSlug)", example = "free") @PathVariable String boardSlug,
        @Parameter(description = "게시글 인덱스", example = "1") @PathVariable Long postIdx
    ) throws Exception {
        if(postService.isPostSecret(postIdx)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        PostResponseDto postInfo = postService.selectPost(postIdx);
        
        if(postInfo!=null){
            return ResponseEntity.ok().body(postInfo);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
        summary = "비밀글 인증 및 조회",
        description = "비밀글에 대해 패스워드를 입력받아 인증 후, 해당 게시글을 반환합니다."
    )
    @ApiResponse(
        responseCode = "200", description = "조회 성공"
        
    )
    @ApiResponse(
        responseCode = "400", description = "비밀번호 틀림, 잘못된 요청"
    )
    @PostMapping("/{postIdx}/auth")
    public ResponseEntity<PostResponseDto> selectPostBySecret(
        @Parameter(description = "게시판 식별자(boardSlug)", example = "free") @PathVariable String boardSlug,
        @Parameter(description = "게시글 인덱스", example = "1") @PathVariable Long postIdx,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "비밀글 인증 요청 정보(password)", required = true)
        @RequestBody PostSecretRequestDto request
    ) throws Exception {
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
    
    @Operation(
        summary = "게시글 작성",
        description = "게시판에 새로운 게시글을 작성합니다."
    )
    @ApiResponse(
        responseCode = "200", description = "작성 성공",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(value = "작성성공")
        )
    )
    @ApiResponse(
        responseCode = "400", description = "잘못된 요청(예: 입력값 누락 등)",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(value = "작성실패")
        )
    )
    @PostMapping
    public ResponseEntity<String> insertPost(
        @Parameter(description = "게시판 식별자(boardSlug)", example = "free") @PathVariable String boardSlug,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "게시글 작성 정보", required = true)
        @RequestBody PostRequestDto post
    ) throws Exception {
        int result=postService.insertPostByBoardSlug(post,boardSlug);
        if(result>0){
            return ResponseEntity.ok().body("작성성공");
        }else{
            return ResponseEntity.badRequest().body("작성실패");
        }
    }

    @Operation(
        summary = "게시글 수정",
        description = "기존 게시글을 수정합니다."
    )
    @ApiResponse(
        responseCode = "200", description = "수정 성공",
        content = @Content(
            mediaType = "text/plain",
            examples = @ExampleObject(value = "수정성공")
        )
    )
    @ApiResponse(
        responseCode = "400", description = "잘못된 요청",
        content = @Content(
                mediaType = "text/plain",
                examples = @ExampleObject(value = "수정실패")
        )
    )
    @PutMapping("/{postIdx}")
    public ResponseEntity<String> updatePost(
        @Parameter(description = "게시판 식별자(slug)", example = "free") @PathVariable String boardSlug,
        @Parameter(description = "게시글 인덱스", example = "1") @PathVariable Long postIdx,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "게시글 수정 정보", required = true)
        @RequestBody PostUpdateRequestDto postUpdateRequest
    ) throws Exception {
        int result=postService.updatePostByBoardSlug(postIdx,boardSlug,postUpdateRequest);
        if(result>0){
            return ResponseEntity.ok().body("수정성공");
        }else{
            return ResponseEntity.badRequest().body("수정실패");
        }
    }

    @Operation(
        summary = "게시글 삭제",
        description = "해당 게시글을 삭제합니다."
    )
    @ApiResponse(
    responseCode = "200", description = "성공 시 메시지",
    content = @Content(
            mediaType = "text/plain",
            examples = @ExampleObject(value = "삭제성공")
        )
    )
    @ApiResponse(
        responseCode = "400", description = "잘못된 요청",
        content = @Content(
                mediaType = "text/plain",
                examples = @ExampleObject(value = "삭제실패")
        )
    )
    @DeleteMapping("/{postIdx}")
    public ResponseEntity<String> deletePost(
        @Parameter(description = "게시판 식별자(slug)", example = "free") @PathVariable String boardSlug,
        @Parameter(description = "게시글 인덱스", example = "1") @PathVariable Long postIdx
    ) throws Exception {
        int result=postService.softDeletePost(postIdx);
        if(result>0){
            return ResponseEntity.ok().body("삭제성공");
        }else{
            return ResponseEntity.ok().body("삭제실패");
        }
    }
}

