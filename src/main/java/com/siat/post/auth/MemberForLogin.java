package com.siat.post.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberForLogin {
    private String userId;
    private String userPassword;
}
