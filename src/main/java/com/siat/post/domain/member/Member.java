package com.siat.post.domain.member;

import com.siat.post.domain.member.dto.MemberSelectResponseDto;
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

    public MemberSelectResponseDto toDto() {
        return MemberSelectResponseDto.builder()
                .userId(this.userId)
                .userName(this.userName)
                .userEmail(this.userEmail)
                .userNickname(this.userNickname)
                .grade(this.grade)
                .userPoint(this.userPoint)
                .userThumbnailFileUrl(this.userThumbnailFileUrl)
                .profileDescription(this.profileDescription)
                .regDate(this.regDate)
                .updateDate(this.updateDate)
                .build();
    }

}
