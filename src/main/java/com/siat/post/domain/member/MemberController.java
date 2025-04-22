package com.siat.post.domain.member;

import com.siat.post.domain.member.dto.MemberInsertRequestDto;
import com.siat.post.domain.member.dto.MemberSelectResponseDto;
import com.siat.post.domain.member.dto.MemberUpdateRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

import java.util.List;

@Tag(name = "Member API", description = "회원 관련 API")
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원 정보 조회", description = "회원 번호를 통해 특정 회원의 정보를 조회합니다.")
    @GetMapping("/select")
    public ResponseEntity<MemberSelectResponseDto> selectMember(@RequestParam("id") int id, Model model) throws Exception{
        return ResponseEntity.ok(memberService.selectMember(id));
    }

    @GetMapping("/selectAll")
    public ResponseEntity<List<MemberSelectResponseDto>> selectAllMembers() throws Exception {
        List<MemberSelectResponseDto> members = memberService.selectMembers();
        return ResponseEntity.ok(members);
    }

    @Parameter(
            name = "grade",
            description = "회원 등급",
            example = "USER",
            required = true,
            schema = @Schema(allowableValues = {"USER", "ADMIN"})
    )
    @Operation(summary = "회원 등록", description = "새로운 회원을 등록합니다.")
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

    @PutMapping("/update")
    public ResponseEntity<String> updateMember(@RequestBody MemberUpdateRequestDto requestDto,
                                               BindingResult bindingResult,
                                               Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "입력값이 올바르지 않습니다.");
            return ResponseEntity.badRequest().body("입력값이 올바르지 않습니다.");
        }

        memberService.updateMember(requestDto);

        return ResponseEntity.ok("회원 정보가 성공적으로 수정되었습니다.");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMember(@RequestParam("id") int id) throws Exception {
        memberService.deleteMember(id);
        return ResponseEntity.ok("회원 정보가 성공적으로 삭제되었습니다.");
    }
}
