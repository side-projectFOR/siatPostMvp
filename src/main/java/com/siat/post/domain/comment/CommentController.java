package com.siat.post.domain.comment;

import com.siat.post.domain.comment.dto.CommentInsertRequestDto;
import com.siat.post.domain.comment.dto.CommentSelectResponseDto;
import com.siat.post.domain.post.dto.PostSimpleInfoResponseDto;
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

}
