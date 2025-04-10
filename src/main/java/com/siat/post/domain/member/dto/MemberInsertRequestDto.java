package com.siat.post.domain.member.dto;

import com.siat.post.domain.member.Member;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberInsertRequestDto {
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userNickname;
    private String grade; // ENUM('USER', 'ADMIN')
    private String userThumbnailFileUrl;
    private String profileDescription;

    public Member toEntity() {
        return Member.builder()
                .userId(this.userId)
                .userPassword(this.userPassword)
                .userName(this.userName)
                .userEmail(this.userEmail)
                .userNickname(this.userNickname != null ? this.userNickname : this.userName)
                .grade(this.grade != null ? this.grade : "USER")
                .userPoint(0L)
                .userThumbnailFileUrl(this.userThumbnailFileUrl)
                .profileDescription(this.profileDescription)
                .regDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .isDelete(0)
                .build();
    }
}
