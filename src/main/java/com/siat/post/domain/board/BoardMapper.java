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
    BoardResponseDto selectBoardByIdx(Integer boardIdx) throws SQLException;
    BoardResponseDto selectBoardBySlug(String boardSlug) throws SQLException;
    int insertBoard(BoardRequestDto boardRequest) throws SQLException;
    int updateBoardByIdx(BoardUpdateRequestDto boardUpdateRequest) throws SQLException;
    int updateBoardBySlug(BoardUpdateRequestDto boardUpdateRequest) throws SQLException;
    int softDeleteBoardByIdx(Integer boardIdx) throws SQLException;
    int softDeleteBoardBySlug(String boardSlug) throws SQLException;
}