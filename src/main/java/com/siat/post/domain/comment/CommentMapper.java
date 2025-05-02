package com.siat.post.domain.comment;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    int insertComment(Comment comment);
}
