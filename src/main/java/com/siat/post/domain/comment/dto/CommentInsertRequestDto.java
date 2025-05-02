package com.siat.post.domain.comment.dto;

import com.siat.post.domain.comment.Comment;
import com.siat.post.domain.member.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentInsertRequestDto {
    @Schema(description = "작성자(회원) 고유 인덱스. 비회원일 경우 null", example = "1")
    private Integer userIdx;

    @Schema(description = "댓글이 달릴 게시글 고유 인덱스", example = "1")
    private Long postIdx;

    @Schema(description = "부모 댓글 인덱스(대댓글일 경우), 최상위 댓글이면 null", example = "789")
    private Long commentParentIdx;

    @Schema(description = "댓글 내용", example = "이 글 정말 유익하네요!")
    private String commentContent;

    @Schema(description = "댓글 작성자 이름 또는 닉네임", example = "댓글러")
    private String commentAuthor;

    @Schema(description = "삭제 여부(0: 정상, 1: 삭제)", example = "0", defaultValue = "0")
    private Integer isDelete;

    public Comment toEntity() {
        return Comment.builder()
                .userIdx(this.userIdx)
                .commentParentIdx(this.commentParentIdx)
                .commentContent(this.commentContent)
                .commentAuthor(this.commentAuthor)
                .isDelete(this.isDelete)
                .build();
    }
}
