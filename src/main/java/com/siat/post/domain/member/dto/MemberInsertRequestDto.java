package com.siat.post.domain.member.dto;

import com.siat.post.domain.member.Member;
import com.siat.post.domain.member.MemberGrade;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberInsertRequestDto {
    @NotBlank(message = "ID는 필수입니다.")
    @Schema(description = "사용자 ID", example = "siat2025")
    private String userId;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Schema(description = "사용자 비밀번호", example = "P@ssw0rd!")
    private String userPassword;

    @NotBlank(message = "이름은 필수입니다.")
    @Schema(description = "사용자 이름", example = "홍길동")
    private String userName;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    @Schema(description = "이메일 주소", example = "user@example.com")
    private String userEmail;

    @NotBlank(message = "닉네임은 필수입니다.")
    @Schema(description = "사용자 닉네임", example = "길동이")
    private String userNickname;

    @NotNull(message = "회원 등급은 필수입니다.")
    @Schema(description = "회원 등급", example = "USER", allowableValues = {"USER", "ADMIN"})
    private MemberGrade grade; // ENUM('USER', 'ADMIN')

    @Schema(description = "프로필 썸네일 이미지 URL", example = "https://cdn.example.com/images/profile.jpg")
    private String userThumbnailFileUrl;

    @Schema(description = "프로필 소개 문구", example = "안녕하세요, 홍길동입니다.")
    private String profileDescription;

    public Member toEntity() {
        return Member.builder()
                .userId(this.userId)
                .userPassword(this.userPassword)
                .userName(this.userName)
                .userEmail(this.userEmail)
                .userNickname(this.userNickname != null ? this.userNickname : this.userName)
                .grade((this.grade != null ? this.grade : MemberGrade.USER).name())
                .userPoint(0L)
                .userThumbnailFileUrl(this.userThumbnailFileUrl)
                .profileDescription(this.profileDescription)
                .regDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .isDelete(0)
                .build();
    }
}
