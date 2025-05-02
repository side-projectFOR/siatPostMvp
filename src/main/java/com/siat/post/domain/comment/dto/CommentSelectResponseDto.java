package com.siat.post.domain.comment.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentSelectResponseDto {
    private Long commentIdx;
    private Integer userIdx;
    private Long postIdx;
    private Long commentParentIdx;
    private String commentContent;
    private String commentAuthor;
    private Integer isDelete;
}
