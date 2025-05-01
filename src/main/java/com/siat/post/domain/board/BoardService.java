package com.siat.post.domain.board;

import java.sql.SQLException;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.siat.post.domain.board.dto.Board;
import com.siat.post.domain.board.dto.BoardRequestDto;
import com.siat.post.domain.board.dto.BoardResponseDto;
import com.siat.post.domain.board.dto.BoardUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    public List<BoardResponseDto> selectBoards() throws Exception {
        return boardMapper.selectBoards();
    }

    public BoardResponseDto selectBoardByIdx(int boardIdx) throws Exception {
        BoardResponseDto boardInfo = boardMapper.selectBoardByIdx(boardIdx);
        if(boardInfo!=null){
            if(!boardInfo.getIsDelete()){
                return boardInfo;
            }
        }
        return null;
    }
    public BoardResponseDto selectBoardBySlug(String boardSlug) throws Exception {
        BoardResponseDto boardInfo = boardMapper.selectBoardBySlug(boardSlug);
        if(boardInfo!=null){
            if(!boardInfo.getIsDelete()){
                return boardInfo;
            }
        }
        return null;
    }
    @CacheEvict(cacheNames = "board", key="#boardRequest.boardSlug")
    public int insertBoard(BoardRequestDto boardRequest) throws Exception {
        if (boardMapper.existsByBoardSlug(boardRequest.getBoardSlug())) {
            throw new DuplicateKeyException("boardSlug 중복");
        }
        Board board = Board.builder()
                .boardDescription(boardRequest.getBoardDescription())
                .boardName(boardRequest.getBoardName())
                .boardSlug(boardRequest.getBoardSlug())
                .build();
        return boardMapper.insertBoard(board);
    }
    
    @CacheEvict(cacheNames = "board")
    public int updateBoardBySlug(BoardUpdateRequestDto boardUpdateRequest) throws Exception {
        return boardMapper.updateBoardBySlug(boardUpdateRequest);
    }

    @CacheEvict(cacheNames = "board")
    public int softDeleteBoardBySlug(String boardSlug) throws Exception {
        return boardMapper.softDeleteBoardBySlug(boardSlug);
    }

    public boolean isVaildBoardSlug(String boardSlug){
        return !StringUtils.hasText(boardSlug);
    }

    @Cacheable(cacheNames = "board", key = "#boardSlug")
    public Integer selectBoardIdxByboardSlug(String boardSlug) throws Exception {
       return boardMapper.selectBoardIdxByboardSlug(boardSlug);
    }
}