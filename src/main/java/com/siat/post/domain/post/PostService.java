package com.siat.post.domain.post;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.siat.post.domain.post.dto.Post;
import com.siat.post.domain.post.dto.PostRequestDto;
import com.siat.post.domain.post.dto.PostResponseDto;
import com.siat.post.domain.post.dto.PostUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;

    public PostResponseDto selectPost(long postIdx) throws Exception {
        Post post = postMapper.selectPost(postIdx);
        if (post != null) {
            return post.toDto();
        } else {
            return null;
        }
    }
    public List<PostResponseDto> selectPostsByBoard(String boardSlug) throws Exception{
        List<Post> postList = postMapper.selectPostsByBoard(boardSlug);
        return postList.stream().map(Post::toDto).toList();
    }
    public List<PostResponseDto> selectPosts() throws Exception{
        List<Post> postList = postMapper.selectPosts();
        return postList.stream().map(Post::toDto).toList();
    }
    public int insertPost(PostRequestDto postRequest) throws Exception{
        Post post = Post.builder()
                        .boardIdx(postRequest.getBoardIdx())
                        .userIdx(postRequest.getUserIdx())
                        .postAuthor(postRequest.getPostAuthor())
                        .postTitle(postRequest.getPostTitle())
                        .postContent(postRequest.getPostContent())
                        .postPassword(postRequest.getPostPassword())
                        .isSecret(postRequest.getIsSecret())
                        .build();
        return  postMapper.insertPost(post);
    }
    public int updatePost(long postIdx, PostUpdateRequestDto postUpdateRequest) throws Exception {
        PostUpdateRequestDto post = PostUpdateRequestDto.builder()
                        .boardIdx(postUpdateRequest.getBoardIdx())
                        .postAuthor(postUpdateRequest.getPostAuthor())
                        .postTitle(postUpdateRequest.getPostTitle())
                        .postContent(postUpdateRequest.getPostContent())
                        .hit(postUpdateRequest.getHit())
                        .isSecret(postUpdateRequest.getIsSecret())
                        .postPassword(postUpdateRequest.getPostPassword())
                        .isDelete(postUpdateRequest.getIsDelete())
                        .updateDate(LocalDateTime.now())
                        .build();
    
        return postMapper.updatePost(post);
    }

    public int softDeltePost(Long postIdx) throws Exception {
        
        return postMapper.softDeletePost(postIdx);
    }
}
