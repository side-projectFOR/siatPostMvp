package com.siat.post.domain.post;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.siat.post.domain.post.dto.Like;
import com.siat.post.domain.post.dto.LikeInfoDto;
import com.siat.post.domain.post.dto.LikeRequestDto;
import com.siat.post.domain.post.dto.Post;
@Mapper
public interface LikeMapper {

    public boolean existsLike(LikeRequestDto like);

    public int insertLike(Like like);

    public int deleteLike(LikeRequestDto like);

    public List<LikeInfoDto> selectLikeByPostIdx(Long postIdx);

    public List<LikeInfoDto> selectAllLikeByPost(List<Long> postIdxList);

    public int countLikeByPostIdx(Long postIdx);
}

