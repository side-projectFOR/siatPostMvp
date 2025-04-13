package com.siat.post.domain.post.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PostUpdateRequestDto {
    private Integer boardIdx;
    private String postAuthor;
    private String postTitle;
    private String postContent;
    private int hit;
    private Boolean isSecret;
    private String postPassword;
    private LocalDateTime updateDate;
    private Boolean isDelete;
}
