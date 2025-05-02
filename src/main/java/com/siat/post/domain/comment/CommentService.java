package com.siat.post.domain.comment;

import com.siat.post.domain.comment.dto.CommentInsertRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentMapper commentMapper;

    public int insertComment(Long postIdx, CommentInsertRequestDto commentRequestDto) {
        commentRequestDto.setPostIdx(postIdx);
        return commentMapper.insertComment(commentRequestDto.toEntity());
    }
}
