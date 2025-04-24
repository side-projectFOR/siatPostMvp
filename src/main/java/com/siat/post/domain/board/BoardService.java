package com.siat.post.domain.board;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public int insertBoard(BoardRequestDto boardRequest) throws Exception {
        Board board = Board.builder()
                        .boardDescription(boardRequest.getBoardDescription())
                        .boardName(boardRequest.getBoardName())
                        .boardSlug(boardRequest.getBoardSlug())
                        .build();
        return boardMapper.insertBoard(board);
    }

    public int updateBoardBySlug(String boardSlug,BoardUpdateRequestDto boardUpdateRequest) throws Exception {
        boardUpdateRequest.setBoardSlug(boardSlug);
        return boardMapper.updateBoardBySlug(boardUpdateRequest);
    }

    public int softDeleteBoardBySlug(String boardSlug) throws Exception {
        return boardMapper.softDeleteBoardBySlug(boardSlug);
    }
}