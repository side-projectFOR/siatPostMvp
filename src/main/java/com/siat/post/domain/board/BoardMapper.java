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
    List<BoardResponseDto> selectBoards();
    BoardResponseDto selectBoardByIdx(Integer boardIdx);
    BoardResponseDto selectBoardBySlug(String boardSlug);
    Integer selectBoardIdxByboardSlug(String boardSlug);
    int insertBoard(Board boardRequest);
    int updateBoardByIdx(BoardUpdateRequestDto boardUpdateRequest);
    int updateBoardBySlug(BoardUpdateRequestDto boardUpdateRequest);
    int softDeleteBoardByIdx(Integer boardIdx);
    int softDeleteBoardBySlug(String boardSlug);
    boolean existsByBoardSlug(@Param("boardSlug") String boardSlug);
}