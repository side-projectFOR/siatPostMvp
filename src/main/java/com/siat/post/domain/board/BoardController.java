package com.siat.post.domain.board;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siat.post.domain.board.dto.BoardRequestDto;
import com.siat.post.domain.board.dto.BoardResponseDto;
import com.siat.post.domain.board.dto.BoardUpdateRequestDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    @Operation(summary = "모든 게시판 조회", description = "삭제되지 않은 모든 게시판 목록 반환")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public @ResponseBody ResponseEntity<List<BoardResponseDto>> selectBoards() throws Exception {
        List<BoardResponseDto> boardList = boardService.selectBoards();
        if (boardList != null) {
            return ResponseEntity.ok().body(boardList);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{boardSlug}")
    @Operation(summary = "특정 게시판 조회", description = "슬러그를 이용해 특정 게시판 반환")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public @ResponseBody ResponseEntity<? super BoardResponseDto> selectBoard(@PathVariable String boardSlug) throws Exception {
        if(isVaildBoardSlug(boardSlug)){
            return ResponseEntity.badRequest().body("요청값 잘못됨");
        }
        BoardResponseDto board = boardService.selectBoardBySlug(boardSlug);
        if (board != null) {
            return ResponseEntity.ok().body(board);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping
    @Operation(summary = "새로운 게시판 생성", description = "제공된 정보로 새로운 게시판 생성")
    @ApiResponse(responseCode = "200", description = "생성 성공")
    @ApiResponse(responseCode = "400", description = "생성 실패")
    public @ResponseBody ResponseEntity<String> insertBoard(@RequestBody BoardRequestDto boardRequest) throws Exception {
        int result = boardService.insertBoard(boardRequest);
        if (result > 0) {
            return ResponseEntity.ok().body("게시판 생성 성공");
        } else {
            return ResponseEntity.badRequest().body("게시판 생성 실패");
        }
    }

    @PutMapping("/{boardSlug}")
    @Operation(summary = "게시판 수정", description = "슬러그로 특정 게시판 정보 수정")
    @ApiResponse(responseCode = "200", description = "수정 성공")
    @ApiResponse(responseCode = "400", description = "수정 실패")
    public @ResponseBody ResponseEntity<String> updateBoard(@PathVariable String boardSlug, @RequestBody BoardUpdateRequestDto boardUpdateRequest) throws Exception {
        if(isVaildBoardSlug(boardSlug)){
            return ResponseEntity.badRequest().body("요청값 잘못됨");
        }
        int result = boardService.updateBoardBySlug(boardSlug, boardUpdateRequest);
        if (result > 0) {
            return ResponseEntity.ok().body("게시판 수정 성공");
        } else {
            return ResponseEntity.badRequest().body("게시판 수정 실패");
        }
    }

    @DeleteMapping("/{boardSlug}")
    @Operation(summary = "게시판 삭제", description = "is_delete 플래그를 이용한 게시판 소프트 삭제")
    @ApiResponse(responseCode = "200", description = "삭제 성공")
    @ApiResponse(responseCode = "400", description = "삭제 실패")
    public @ResponseBody ResponseEntity<String> deleteBoard(@PathVariable String boardSlug) throws Exception {
        if(isVaildBoardSlug(boardSlug)){
            return ResponseEntity.badRequest().body("요청값 잘못됨");
        }
        int result = boardService.softDeleteBoardBySlug(boardSlug);
        if (result > 0) {
            return ResponseEntity.ok().body("게시판 삭제 성공");
        } else {
            return ResponseEntity.badRequest().body("게시판 삭제 실패");
        }
    }

    private boolean isVaildBoardSlug(String boardSlug){
        return !StringUtils.hasText(boardSlug);
    }
}