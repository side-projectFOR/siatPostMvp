package com.siat.post.domain.comment;

import com.siat.post.domain.comment.dto.CommentInsertRequestDto;
import com.siat.post.domain.comment.dto.CommentSelectResponseDto;
import com.siat.post.domain.comment.dto.CommentUpdateRequestDto;
import com.siat.post.domain.post.dto.PostSimpleInfoResponseDto;
import com.siat.post.domain.post.dto.PostUpdateRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{postIdx}/comments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "CommentController", description = "댓글 CRUD API")
public class CommentController {
    private final CommentService commentService;

    @Operation(
            summary = "댓글 작성",
            description = "게시판에 해당하는 새로운 댓글을 작성합니다."
    )
    @ApiResponse(
            responseCode = "200", description = "작성 성공",
            content = @Content(
                    mediaType = "text/plain",
                    examples = @ExampleObject(value = "작성성공")
            )
    )
    @ApiResponse(
            responseCode = "400", description = "잘못된 요청(예: 입력값 누락 등)",
            content = @Content(
                    mediaType = "text/plain",
                    examples = @ExampleObject(value = "작성실패")
            )
    )
    @PostMapping
    public ResponseEntity<String> insertComment(
            @Parameter(description = "작성글 idx", example = "1") @PathVariable Long postIdx,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "댓글 작성 정보", required = true)
            @RequestBody CommentInsertRequestDto commentRequestDto
    ) throws Exception {
        if (postIdx == null) {
            return ResponseEntity.badRequest().body("작성실패");
        }
        int result = commentService.insertComment(postIdx, commentRequestDto);
        if(result>0){
            return ResponseEntity.ok().body("작성성공");
        }else{
            return ResponseEntity.badRequest().body("작성실패");
        }
    }

    @Operation(
            summary = "게시글별 댓글 목록 조회",
            description = "특정 게시글의 모든 댓글 목록을 조회합니다."
    )
    @ApiResponse(responseCode = "200", description = "댓글 목록 조회 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    @GetMapping
    public ResponseEntity<List<CommentSelectResponseDto>> selectComments(
            @Parameter(description = "작성글 idx", example = "1") @PathVariable Long postIdx
    ) throws Exception {
        if (postIdx == null) {
            return ResponseEntity.badRequest().build();
        }
        List<CommentSelectResponseDto> commentList = commentService.selectComments(postIdx);

        if (commentList != null) {
            return ResponseEntity.ok().body(commentList);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "댓글 수정",
            description = "기존 댓글을 수정합니다."
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
    @PutMapping("/{commentIdx}")
    public ResponseEntity<String> updatePost(
            @Parameter(description = "댓글 인덱스", example = "1") @PathVariable Long commentIdx,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "댓글 수정 정보", required = true)
            @RequestBody CommentUpdateRequestDto commentUpdateRequestDto
    ) throws Exception {
        if (commentIdx == null || commentUpdateRequestDto == null) {
            return ResponseEntity.badRequest().body("수정실패");
        }
        int result = commentService.updateComment(commentIdx, commentUpdateRequestDto);
        if(result>0){
            return ResponseEntity.ok().body("수정성공");
        }else{
            return ResponseEntity.badRequest().body("수정실패");
        }
    }

    @Operation(
            summary = "댓글 삭제",
            description = "해당 댓글을 삭제합니다."
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
    @DeleteMapping("/{commentIdx}")
    public ResponseEntity<String> deletePost(
            @Parameter(description = "게시글 인덱스", example = "1") @PathVariable Long commentIdx
    ) throws Exception {
        int result = commentService.softDeleteComment(commentIdx);
        if(result>0){
            return ResponseEntity.ok().body("삭제성공");
        }else{
            return ResponseEntity.ok().body("삭제실패");
        }
    }

}
