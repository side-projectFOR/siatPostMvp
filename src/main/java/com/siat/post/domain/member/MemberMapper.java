package com.siat.post.domain.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    int insertMember(Member member) throws Exception;

    Member selectMember(int id);
}
