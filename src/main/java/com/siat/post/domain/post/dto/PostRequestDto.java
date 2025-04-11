package com.siat.post.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
    private int boardIdx;
    private Integer userIdx;
    private String postAuthor;
    private String postTitle;
    private String postContent;
    private String postPassword;
    private boolean isSecret;
    
    public int getIsSecrect(){
        return isSecret?1:0;
    }
}
