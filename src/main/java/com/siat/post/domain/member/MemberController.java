package com.siat.post.domain.member;

import com.siat.post.domain.member.dto.MemberInsertRequestDto;
import com.siat.post.domain.member.dto.MemberSelectResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member API", description = "회원 관련 API")
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

//        @GetMapping("/selectAll")
//        public String selectMembers(Model model) throws Exception{
//            return "index.html";
//        }

        @Operation(summary = "회원 정보 조회", description = "ID를 통해 특정 회원의 정보를 조회합니다.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "성공",
                        content = @Content(schema = @Schema(implementation = MemberSelectResponseDto.class))),
                @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음",
                        content = @Content(schema = @Schema(type = "string")))
        })
        @GetMapping("/select")
        public ResponseEntity<MemberSelectResponseDto> selectMember(@RequestParam("id") int id, Model model) throws Exception{
            model.addAttribute("id", id);
            return ResponseEntity.ok(memberService.selectMember(id));
        }

        @Operation(summary = "회원 등록", description = "새로운 회원을 등록합니다.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "회원 등록 성공",
                        content = @Content(schema = @Schema(type = "string"))),
                @ApiResponse(responseCode = "400", description = "잘못된 요청",
                        content = @Content(schema = @Schema(type = "string"))),
                @ApiResponse(responseCode = "500", description = "서버 오류",
                        content = @Content(schema = @Schema(type = "string")))
        })
        @PostMapping("/insert")
        public ResponseEntity<String> insertMember(@RequestBody MemberInsertRequestDto requestDto,
                                                   BindingResult bindingResult,
                                                   Model model) throws Exception {
            if (bindingResult.hasErrors()) {
                model.addAttribute("errorMessage", "입력값이 올바르지 않습니다.");
            }

            memberService.insertMember(requestDto);

            return ResponseEntity.ok("회원 등록이 성공적으로 완료되었습니다.");
        }
}
