package com.siat.post.domain.post.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PostResponseDto {
    private Long postIdx;
    private Integer boardIdx;
    private Integer userIdx;
    private String postAuthor;
    private String postTitle;
    private String postContent;
    private int hit;
    private Boolean isSecret;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private Boolean isDelete;
}
