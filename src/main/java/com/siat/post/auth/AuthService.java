package com.siat.post.auth;

import com.siat.post.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthMapper authMapper;

    public boolean isValidUser(String userId, String password) {
        Member member = authMapper.findByUserId(userId);
        System.out.println("member = " + member.getUserPassword());
        if (member != null) {
            return password.equals(member.getUserPassword());
        }
        return false;
    }
}
