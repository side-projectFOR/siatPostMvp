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

    public BoardResponseDto selectBoard(int boardIdx) throws Exception {
        return boardMapper.selectBoard(boardIdx);
    }

    public int insertBoard(BoardRequestDto boardRequest) throws Exception {
        return boardMapper.insertBoard(boardRequest);
    }

    public int updateBoard(int boardIdx, BoardUpdateRequestDto boardUpdateRequest) throws Exception {
        return boardMapper.updateBoard(boardIdx, boardUpdateRequest);
    }

    public int softDeleteBoard(int boardIdx) throws Exception {
        return boardMapper.softDeleteBoard(boardIdx);
    }
}