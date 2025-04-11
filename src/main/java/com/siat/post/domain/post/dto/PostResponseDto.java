package com.siat.post.domain.post.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PostResponseDto {
    private long postIdx;
    private int boardIdx;
    private Integer userIdx;
    private String postAuthor;
    private String postTitle;
    private String postContent;
    private int hit;
    private boolean isSecret;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private boolean isDelete;
}
