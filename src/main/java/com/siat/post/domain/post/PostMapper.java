package com.siat.post.domain.post;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.siat.post.domain.post.dto.Post;
import com.siat.post.domain.post.dto.PostDeleteRequestDto;
import com.siat.post.domain.post.dto.PostUpdateRequestDto;

@Mapper
public interface PostMapper {
    public Post selectPost(long postIdx) throws SQLException;
    public List<Post> selectPosts() throws SQLException;
    public int insertPost(Post post) throws SQLException;
    public int updatePost(PostUpdateRequestDto post) throws SQLException;
    public int updatePostDelete(PostDeleteRequestDto post);
    // public int deletePost(long postIdx);

}
