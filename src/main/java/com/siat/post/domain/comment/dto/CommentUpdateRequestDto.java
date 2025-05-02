package com.siat.post.domain.comment.dto;

import com.siat.post.domain.comment.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentUpdateRequestDto {
    private Long commentIdx;

    @Schema(description = "댓글 내용", example = "이 글 정말 유익하네요!")
    private String commentContent;

    @Schema(description = "댓글 작성자 이름 또는 닉네임", example = "댓글러")
    private String commentAuthor;

    public Comment toEntity() {
        return Comment.builder()
                .commentIdx(commentIdx)
                .commentContent(this.commentContent)
                .commentAuthor(this.commentAuthor)
                .build();
    }
}
