package com.siat.post.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostSecretRequestDto {
    private Long postIdx;
    private String postPassword;
}
