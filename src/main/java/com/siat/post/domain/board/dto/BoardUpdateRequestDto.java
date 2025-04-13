package com.siat.post.domain.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequestDto {
    private String boardName;
    private String boardDescription;
    private String boardSlug;
    private LocalDateTime updateDate;
    private Boolean isDelete;
}