package com.siat.post.domain.post.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post {
    private Long postIdx;
    private Integer boardIdx;
    private Integer userIdx;
    private String postAuthor;
    private String postTitle;
    private String postContent;
    private int hit;
    private Boolean isSecret;
    private String postPassword;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private Boolean isDelete;

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
