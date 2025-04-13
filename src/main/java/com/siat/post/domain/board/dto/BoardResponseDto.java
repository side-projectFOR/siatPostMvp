package com.siat.post.domain.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardResponseDto {
    private Integer boardIdx;
    private String boardName;
    private String boardDescription;
    private String boardSlug;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private Boolean isDelete;
}