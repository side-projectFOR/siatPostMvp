package com.siat.post.domain.post.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class PostUpdateRequestDto {
    @Schema(hidden = true)
    private Integer boardIdx;
    @Schema(hidden = true)
    private String boardSlug;
    @Schema(hidden = true)
    private Long postIdx;
    private String postAuthor;
    private String postTitle;
    private String postContent;
    private Boolean isSecret;
    private String postPassword;
    // @Schema(hidden = true)
    // private LocalDateTime updateDate;
    @Schema(hidden = true)
    private Boolean isDelete;
}
