package com.siat.post.domain.post.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeInfoDto {
    private Long likeIdx;
    private Long postIdx;
    private Integer userIdx;
    private LocalDateTime regDate;
}