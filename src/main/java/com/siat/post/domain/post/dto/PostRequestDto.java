package com.siat.post.domain.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostRequestDto {
    @Schema(description = "작성자(회원) 고유 인덱스. 비회원일 경우 null", example = "123")
    private Integer userIdx;

    @Schema(description = "작성자 이름 혹은 닉네임",example = "닉네임")
    private String postAuthor;

    @Schema(description = "게시글 제목", example ="제목")
    private String postTitle;

    @Schema(description = "게시글 내용", example="글내용")
    private String postContent;

    @Schema(description = "비밀글 비밀번호. 일반 게시글이면 생략 또는 null", example = "1234")
    private String postPassword;

    @Schema(description = "비밀글 여부", example = "false")
    private Boolean isSecret;
}
