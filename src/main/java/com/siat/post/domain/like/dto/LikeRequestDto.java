package com.siat.post.domain.like.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LikeRequestDto {
    private Long postIdx;
    private Integer userIdx;
}
