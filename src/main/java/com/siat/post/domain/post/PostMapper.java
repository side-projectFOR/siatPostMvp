package com.siat.post.domain.post;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.siat.post.domain.post.dto.Post;
import com.siat.post.domain.post.dto.PostResponseDto;
import com.siat.post.domain.post.dto.PostSecretRequestDto;
import com.siat.post.domain.post.dto.PostSimpleInfoResponseDto;
import com.siat.post.domain.post.dto.PostUpdateRequestDto;

@Mapper
public interface PostMapper {
    public Post selectPost(Long postIdx);
    public Post selectPostWithPassword(PostSecretRequestDto post);
    public List<Post> selectPosts();
    public int insertPost(Post post);
    public int updatePost(PostUpdateRequestDto post);
    public int softDeletePost(Long post);
    public List<PostSimpleInfoResponseDto> selectPostsByBoardIdx(Integer boardIdx);
    // public int deletePost(long postIdx);
    public int updatePostHit(Long postIdx); 
    public int selectPostIsSecret(Long postIdx);

}
