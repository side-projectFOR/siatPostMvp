package com.siat.post.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostRequestDto {
    private Integer boardIdx;
    private Integer userIdx;
    private String postAuthor;
    private String postTitle;
    private String postContent;
    private String postPassword;
    private Boolean isSecret;
    
}
