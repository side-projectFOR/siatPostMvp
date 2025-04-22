package com.siat.post.domain.member.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class MemberSelectResponseDto {
    private String userId;
    private String userName;
    private String userEmail;
    private String userNickname;
    private String grade;
    private Long userPoint;
    private String userThumbnailFileUrl;
    private String profileDescription;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
