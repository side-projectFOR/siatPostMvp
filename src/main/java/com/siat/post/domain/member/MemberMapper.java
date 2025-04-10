package com.siat.post.domain.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    int insertMember(Member member) throws Exception;

    Member selectMember(int id) throws Exception;

    List<Member> selectMembers() throws Exception;

    int updateMember(Member member) throws Exception;

    int deleteMember(int id) throws Exception;
}
