package com.siat.post.domain.like;

import java.sql.SQLException;
import java.util.List;

import com.siat.post.domain.like.dto.LikeDeleteRequestDto;
import com.siat.post.domain.like.dto.LikeInfoDto;
import com.siat.post.domain.like.dto.LikeRequestDto;

public interface LikeMapper {
    public int insertLike(LikeRequestDto like) throws SQLException;
    public int deleteLike(LikeDeleteRequestDto like) throws SQLException;
    public List<LikeInfoDto> selectLikeByPost(Long post) throws SQLException;
    
}
