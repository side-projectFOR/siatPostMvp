package com.siat.post.domain.board;

import java.util.List;

import org.springframework.stereotype.Service;

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
        return boardMapper.selectBoardByIdx(boardIdx);
    }
    public BoardResponseDto selectBoardBySlug(String boardSlug) throws Exception {
        return boardMapper.selectBoardBySlug(boardSlug);
    }

    public int insertBoard(BoardRequestDto boardRequest) throws Exception {
        return boardMapper.insertBoard(boardRequest);
    }

    public int updateBoardBySlug(String boardSlug,BoardUpdateRequestDto boardUpdateRequest) throws Exception {
        boardUpdateRequest.setBoardSlug(boardSlug);
        return boardMapper.updateBoardBySlug(boardUpdateRequest);
    }

    public int softDeleteBoardBySlug(String boardSlug) throws Exception {
        return boardMapper.softDeleteBoardBySlug(boardSlug);
    }
}