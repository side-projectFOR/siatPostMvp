package com.siat.post.domain.post.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Post {
    private long postIdx;
    private int boardIdx;
    private Integer userIdx;
    private String postAuthor;
    private String postTitle;
    private String postContent;
    private int hit;
    private boolean isSecret;
    private String postPassword;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private boolean isDelete;

    public int getIsSecrect(){
        return isSecret?1:0;
    }

    public PostResponseDto toDto() {
        return PostResponseDto.builder()
            .postIdx(this.postIdx)
            .boardIdx(this.boardIdx)
            .userIdx(this.userIdx)
            .postAuthor(this.postAuthor)
            .postTitle(this.postTitle)
            .postContent(this.postContent)
            .hit(this.hit)
            .isSecret(this.isSecret)
            .regDate(this.regDate)
            .updateDate(this.updateDate)
            .isDelete(this.isDelete)
            .build();
    }    
}
