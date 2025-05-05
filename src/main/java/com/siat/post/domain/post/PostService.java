package com.siat.post.domain.post;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siat.post.domain.board.BoardService;
import com.siat.post.domain.post.dto.Bookmark;
import com.siat.post.domain.post.dto.BookmarkRequestDto;
import com.siat.post.domain.post.dto.Like;
import com.siat.post.domain.post.dto.LikeInfoDto;
import com.siat.post.domain.post.dto.LikeRequestDto;
import com.siat.post.domain.post.dto.Post;
import com.siat.post.domain.post.dto.PostRequestDto;
import com.siat.post.domain.post.dto.PostResponseDto;
import com.siat.post.domain.post.dto.PostSecretRequestDto;
import com.siat.post.domain.post.dto.PostSimpleInfoResponseDto;
import com.siat.post.domain.post.dto.PostUpdateRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;
    private final BoardService boardService;
    private final LikeMapper likeMapper;
    private final BookmarkMapper bookmarkMapper ;

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
        Integer boardIdx= boardService.selectBoardIdxByboardSlug(boardSlug);
        List<PostSimpleInfoResponseDto> postList = postMapper.selectPostsByBoardIdx(boardIdx);
        List<Long> postIdxList=postList.stream().map(v->v.getPostIdx()).toList();
        return postList;
    }
    public List<PostResponseDto> selectPosts() throws Exception{
        List<Post> postList = postMapper.selectPosts();
        return postList.stream().map(Post::toDto).toList();
    }
    public int insertPostByBoardSlug(PostRequestDto postRequest) throws Exception{
        Integer boardIdx= boardService.selectBoardIdxByboardSlug(postRequest.getBoardSlug());
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
    public int updatePostByBoardSlug(PostUpdateRequestDto postUpdateRequest) throws Exception {
        Integer boardIdx= boardService.selectBoardIdxByboardSlug(postUpdateRequest.getBoardSlug());
        PostUpdateRequestDto post = PostUpdateRequestDto.builder()
                        .postIdx(postUpdateRequest.getPostIdx())
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


    public boolean existsLike(LikeRequestDto like) throws Exception {
        return likeMapper.existsLike(like);
    }

    @Transactional
    public int insertLike(LikeRequestDto request) throws Exception {
        if (likeMapper.existsLike(request)) {
            throw new DuplicateKeyException("이미 좋아요한 게시글입니다.");
        }
        Like like= Like.builder()
                    .userIdx(request.getUserIdx())
                    .postIdx(request.getPostIdx())
                    .build();
        return likeMapper.insertLike(like);
    }

    public int deleteLike(LikeRequestDto like) throws Exception {
        return likeMapper.deleteLike(like);
    }

    public List<LikeInfoDto> selectLikesByPostIdx(Long postIdx) throws Exception {
        return likeMapper.selectLikeByPostIdx(postIdx);
    }

    public int countLikesByPostIdx(Long postIdx) throws Exception {
        return likeMapper.countLikeByPostIdx(postIdx);
    }


    @Transactional
    public int insertBookmark(BookmarkRequestDto request) throws Exception {
        if (bookmarkMapper.existsBookmark(request)) {
            throw new DuplicateKeyException("이미 북마크한한 게시글입니다.");
        }
        Bookmark bookmark= Bookmark.builder()
                    .userIdx(request.getUserIdx())
                    .postIdx(request.getPostIdx())
                    .build();
        return bookmarkMapper.insertBookmark(bookmark);
    }
    public int deleteBookmark(BookmarkRequestDto request) throws Exception {
        return bookmarkMapper.deleteBookmark(request) ;

    }
}
