package com.siat.post.domain.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostSecretRequestDto {
    @Schema(description = "게시글 고유 인덱스", example = "1")
    private Long postIdx;
    @Schema(description = "비밀글 비밀번호")
    private String postPassword;
}
