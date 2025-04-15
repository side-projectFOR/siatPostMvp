package com.siat.post.domain.post;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.siat.post.domain.post.dto.Post;
import com.siat.post.domain.post.dto.PostRequestDto;
import com.siat.post.domain.post.dto.PostResponseDto;
import com.siat.post.domain.post.dto.PostSecretRequestDto;
import com.siat.post.domain.post.dto.PostSimpleInfoResponseDto;
import com.siat.post.domain.post.dto.PostUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;
    // 조회수는 중요하지 않은 듯하여 트랜잭션 안 걸음
    public PostResponseDto selectPost(long postIdx) throws Exception {
        Post post = postMapper.selectPost(postIdx);
        if (post != null) {
            updatePostHit(post.getPostIdx());
            return post.toDto();
        } else {
            return null;
        }
    }
    
    public List<PostSimpleInfoResponseDto> selectPostsByBoard(String boardSlug) throws Exception{
        List<PostSimpleInfoResponseDto> postList = postMapper.selectPostsByBoard(boardSlug);
        return postList;
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
    public int updatePostHit(Long postIdx) throws Exception{
        return postMapper.updatePostHit(postIdx);
    }
    public boolean isPostSecret(Long postIdx) throws Exception{
        return postMapper.selectPostIsSecret(postIdx)==1?true:false;
    }
    public PostResponseDto selectPostWithPassword(PostSecretRequestDto secretPost) throws Exception {
        Post post = postMapper.selectPostWithPassword(secretPost);
        if (post != null) {
            updatePostHit(post.getPostIdx());
            return post.toDto();
        } else {
            return null;
        }
    }
}
