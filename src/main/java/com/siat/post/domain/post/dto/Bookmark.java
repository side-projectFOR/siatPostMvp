package com.siat.post.domain.post.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bookmark {
    private Long likeIdx;
    private Long postIdx;
    private Integer userIdx;
    private LocalDateTime regDate;
}
