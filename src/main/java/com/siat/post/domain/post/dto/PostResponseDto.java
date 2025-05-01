package com.siat.post.domain.post.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostResponseDto {
    @Schema(description = "게시글 인덱스", example = "123")
    private Long postIdx;
    @Schema(description = "게시판 인덱스", example = "1")
    private Integer boardIdx;
    @Schema(description = "작성자 유저 인덱스", example = "42")
    private Integer userIdx;
    @Schema(description = "작성자명", example = "유저")
    private String postAuthor;
    @Schema(description = "게시글 제목", example = "게시글제목")
    private String postTitle;
    @Schema(description = "게시글 내용", example = "게시글내용")
    private String postContent;
    @Schema(description = "조회수", example = "1111")
    private int hit;
    @Schema(description = "비밀글 여부", example = "true")
    private Boolean isSecret;
    @Schema(description = "작성일시", example = "2099-06-11T17:30:00")
    private LocalDateTime regDate;
    @Schema(description = "좋아요 수", example = "1")
    private long likeCnt;
    @Schema(description = "좋아요한 게시글인지", example = "false")
    private Boolean likedByUser;
    @Schema(description = "수정일시", example = "2099-06-11T18:00:00")
    private LocalDateTime updateDate;
    @Schema(description = "삭제 여부", example = "false")
    private Boolean isDelete;
}
