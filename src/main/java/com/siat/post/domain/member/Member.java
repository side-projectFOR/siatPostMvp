package com.siat.post.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class Member {
    private Integer userIdx;
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userNickname;
    private String grade; // ENUM('USER', 'ADMIN')
    private Long userPoint;
    private String userThumbnailFileUrl;
    private String profileDescription;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private Integer isDelete;


}
