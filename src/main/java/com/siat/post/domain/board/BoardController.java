package com.siat.post.domain.board;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import com.siat.post.domain.post.dto.PostResponseDto;

import ch.qos.logback.core.util.StringUtil;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("")
    public @ResponseBody ResponseEntity<List<BoardResponseDto>> getselectBoards() throws Exception {
        List<BoardResponseDto> boardList = boardService.selectBoards();
        if (boardList != null) {
            return ResponseEntity.ok().body(boardList);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{boardIdx}")
    public @ResponseBody ResponseEntity<BoardResponseDto> selectBoard(@PathVariable int boardIdx) throws Exception {
        BoardResponseDto board = boardService.selectBoard(boardIdx);
        if (board != null) {
            return ResponseEntity.ok().body(board);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping
    public @ResponseBody ResponseEntity<String> insertBoard(@RequestBody BoardRequestDto boardRequest) throws Exception {
        int result = boardService.insertBoard(boardRequest);
        if (result > 0) {
            return ResponseEntity.ok().body("게시판 생성 성공");
        } else {
            return ResponseEntity.badRequest().body("게시판 생성 실패");
        }
    }

    @PutMapping("/{boardIdx}")
    public @ResponseBody ResponseEntity<String> updateBoard(@PathVariable int boardIdx, @RequestBody BoardUpdateRequestDto boardUpdateRequest) throws Exception {
        int result = boardService.updateBoard(boardIdx, boardUpdateRequest);
        if (result > 0) {
            return ResponseEntity.ok().body("게시판 수정 성공");
        } else {
            return ResponseEntity.badRequest().body("게시판 수정 실패");
        }
    }

    @DeleteMapping("/{boardIdx}")
    public @ResponseBody ResponseEntity<String> deleteBoard(@PathVariable int boardIdx) throws Exception {
        int result = boardService.softDeleteBoard(boardIdx);
        if (result > 0) {
            return ResponseEntity.ok().body("게시판 삭제 성공");
        } else {
            return ResponseEntity.badRequest().body("게시판 삭제 실패");
        }
    }
}