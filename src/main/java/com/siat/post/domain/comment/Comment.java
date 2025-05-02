package com.siat.post.domain.comment;

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
}
