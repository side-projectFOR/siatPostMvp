package com.siat.post.domain.post;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siat.post.domain.board.BoardMapper;
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
    private final BoardMapper boardMapper;
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
    public List<PostSimpleInfoResponseDto> selectPostsByBoardSlug(String boardSlug) throws Exception{
        Integer boardIdx= boardMapper.selectBoardIdxByboardSlug(boardSlug);
        List<PostSimpleInfoResponseDto> postList = postMapper.selectPostsByBoardIdx(boardIdx);
        return postList;
    }
    public List<PostResponseDto> selectPosts() throws Exception{
        List<Post> postList = postMapper.selectPosts();
        return postList.stream().map(Post::toDto).toList();
    }
    public int insertPostByBoardSlug(PostRequestDto postRequest,String boardSlug) throws Exception{
        Integer boardIdx= boardMapper.selectBoardIdxByboardSlug(boardSlug);
        Post post = Post.builder()
                        .boardIdx(boardIdx)
                        .userIdx(postRequest.getUserIdx())
                        .postAuthor(postRequest.getPostAuthor())
                        .postTitle(postRequest.getPostTitle())
                        .postContent(postRequest.getPostContent())
                        .postPassword(postRequest.getPostPassword())
                        .isSecret(postRequest.getIsSecret())
                        .build();
        return  postMapper.insertPost(post);
    }
    public int updatePostByBoardSlug(Long postIdx, String boardSlug , PostUpdateRequestDto postUpdateRequest) throws Exception {
        Integer boardIdx= boardMapper.selectBoardIdxByboardSlug(boardSlug);
        PostUpdateRequestDto post = PostUpdateRequestDto.builder()
                        .postIdx(postIdx)
                        .boardIdx(boardIdx)
                        .postAuthor(postUpdateRequest.getPostAuthor())
                        .postTitle(postUpdateRequest.getPostTitle())
                        .postContent(postUpdateRequest.getPostContent())
                        .isSecret(postUpdateRequest.getIsSecret())
                        .postPassword(postUpdateRequest.getPostPassword())
                        .isDelete(postUpdateRequest.getIsDelete())
                        .build();
    
        return postMapper.updatePost(post);
    }

    public int softDeletePost(Long postIdx) throws Exception {
        
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
    public Integer selectBoardIdxByBoardSlug(String boardSlug) throws Exception{
        return boardMapper.selectBoardIdxByboardSlug(boardSlug);
    }
}
