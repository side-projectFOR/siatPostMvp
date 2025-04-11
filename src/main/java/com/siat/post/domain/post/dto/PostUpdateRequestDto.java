package com.siat.post.domain.post.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PostUpdateRequestDto {
    private int boardIdx;
    private String postAuthor;
    private String postTitle;
    private String postContent;
    private int hit;
    private boolean isSecret;
    private String postPassword;
    private LocalDateTime updateDate;
    private boolean isDelete;
}
