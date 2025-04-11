package com.siat.post.domain.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PostDeleteRequestDto {
    private long postIdx;
    private boolean isDelete;
}
