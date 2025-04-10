package com.siat.post.domain.member;

import com.siat.post.domain.member.dto.MemberInsertRequestDto;
import com.siat.post.domain.member.dto.MemberSelectResponseDto;
import com.siat.post.domain.member.dto.MemberUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public int insertMember(MemberInsertRequestDto requestDto) throws Exception{
        Member member = requestDto.toEntity();
        return memberMapper.insertMember(member);
    }

    public MemberSelectResponseDto selectMember(int id) throws Exception{
        return memberMapper.selectMember(id).toDto();
    }

    public List<MemberSelectResponseDto> selectMembers() throws Exception{
        List<Member> members = memberMapper.selectMembers();
        return members.stream()
                .map(member -> MemberSelectResponseDto.builder()
                        .userId(member.getUserId())
                        .userName(member.getUserName())
                        .userEmail(member.getUserEmail())
                        .userNickname(member.getUserNickname())
                        .grade(member.getGrade())
                        .userPoint(member.getUserPoint())
                        .userThumbnailFileUrl(member.getUserThumbnailFileUrl())
                        .profileDescription(member.getProfileDescription())
                        .regDate(member.getRegDate())
                        .updateDate(member.getUpdateDate())
                        .build()
                    )
                .collect(Collectors.toList());
    }

    public int updateMember(MemberUpdateRequestDto requestDto) throws Exception{
        Member member = requestDto.toEntity();
        return memberMapper.updateMember(member);
    }

    public int deleteMember(int id) throws Exception{
        return memberMapper.deleteMember(id);
    }
}
