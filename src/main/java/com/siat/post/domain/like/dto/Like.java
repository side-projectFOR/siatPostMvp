package com.siat.post.domain.like.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Like {
    private Long likeIdx;
    private Long postIdx;
    private Integer userIdx;
    private LocalDateTime regDate;
}
