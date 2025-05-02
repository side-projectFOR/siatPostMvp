package com.siat.post.domain.comment;

import com.siat.post.domain.comment.dto.CommentSelectResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    private Long commentIdx;
    private Integer userIdx;
    private Long postIdx;
    private Long commentParentIdx;
    private String commentContent;
    private String commentAuthor;
    private Integer isDelete;

    public CommentSelectResponseDto toDto() {
        return CommentSelectResponseDto.builder()
                .commentIdx(this.commentIdx)
                .userIdx(this.userIdx)
                .postIdx(this.postIdx)
                .commentParentIdx(this.commentParentIdx)
                .commentContent(this.commentContent)
                .commentAuthor(this.commentAuthor)
                .isDelete(this.isDelete)
                .build();
    }
}
