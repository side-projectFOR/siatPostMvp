package com.siat.post.domain.comment;

import com.siat.post.domain.comment.dto.CommentSelectResponseDto;
import com.siat.post.domain.comment.dto.CommentUpdateRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int insertComment(Comment comment);

    List<CommentSelectResponseDto> selectComments(Long postIdx);

    int updateComment(CommentUpdateRequestDto commentUpdateRequestDto);

    int softDeleteComment(Long commentIdx);
}
