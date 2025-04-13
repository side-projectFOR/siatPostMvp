package com.siat.post.domain.board;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.siat.post.domain.board.dto.BoardRequestDto;
import com.siat.post.domain.board.dto.BoardResponseDto;
import com.siat.post.domain.board.dto.BoardUpdateRequestDto;

@Mapper
public interface BoardMapper {
    List<BoardResponseDto> selectBoards() throws SQLException;
    BoardResponseDto selectBoard(int boardIdx) throws SQLException;
    int insertBoard(BoardRequestDto boardRequest) throws SQLException;
    int updateBoard(int boardIdx, BoardUpdateRequestDto boardUpdateRequest) throws SQLException;
    int softDeleteBoard(int boardIdx) throws SQLException;
}