package com.siat.post.domain.comment;

import com.siat.post.domain.comment.dto.CommentInsertRequestDto;
import com.siat.post.domain.comment.dto.CommentSelectResponseDto;
import com.siat.post.domain.post.dto.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentMapper commentMapper;

    public int insertComment(Long postIdx, CommentInsertRequestDto commentRequestDto) {
        commentRequestDto.setPostIdx(postIdx);
        return commentMapper.insertComment(commentRequestDto.toEntity());
    }

    public List<CommentSelectResponseDto> selectComments(Long postIdx) {
        return commentMapper.selectComments(postIdx);
    }
}
