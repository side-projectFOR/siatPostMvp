package com.siat.post.auth;

import com.siat.post.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    Member findByUserId(String id);
}
