package com.siat.post.domain.board;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.siat.post.domain.board.dto.Board;
import com.siat.post.domain.board.dto.BoardResponseDto;
import com.siat.post.domain.board.dto.BoardUpdateRequestDto;

@Mapper
public interface BoardMapper {
    List<BoardResponseDto> selectBoards() throws SQLException;
    BoardResponseDto selectBoardByIdx(Integer boardIdx) throws SQLException;
    BoardResponseDto selectBoardBySlug(String boardSlug) throws SQLException;
    Integer selectBoardIdxByboardSlug(String boardSlug) throws SQLException;
    int insertBoard(Board boardRequest) throws SQLException;
    int updateBoardByIdx(BoardUpdateRequestDto boardUpdateRequest) throws SQLException;
    int updateBoardBySlug(BoardUpdateRequestDto boardUpdateRequest) throws SQLException;
    int softDeleteBoardByIdx(Integer boardIdx) throws SQLException;
    int softDeleteBoardBySlug(String boardSlug) throws SQLException;
    boolean existsByBoardSlug(@Param("boardSlug") String boardSlug);
}