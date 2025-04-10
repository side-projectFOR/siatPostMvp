package com.siat.post.domain.member;

import com.siat.post.domain.member.dto.MemberInsertRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public void insertMember(MemberInsertRequestDto requestDto) throws Exception{
        Member member = requestDto.toEntity();
        memberMapper.insertMember(member);
    }
}
