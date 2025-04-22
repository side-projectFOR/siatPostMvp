package com.siat.post.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board {
    private Integer boardIdx;
    private String boardName;
    private String boardDescription;
    private String boardSlug;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private Boolean isDelete;

    public BoardResponseDto toDto() {
        return BoardResponseDto.builder()
            .boardIdx(this.boardIdx)
            .boardName(this.boardName)
            .boardDescription(this.boardDescription)
            .boardSlug(this.boardSlug)
            .regDate(this.regDate)
            .updateDate(this.updateDate)
            .isDelete(this.isDelete)
            .build();
    }
}