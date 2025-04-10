package com.siat.post.domain.member;

import com.siat.post.domain.member.dto.MemberInsertRequestDto;
import com.siat.post.domain.member.dto.MemberSelectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

//        @GetMapping("/selectAll")
//        public String selectMembers(Model model) throws Exception{
//            return "index.html";
//        }
        @GetMapping("/select")
        public ResponseEntity<MemberSelectResponseDto> selectMember(@RequestParam("id") int id, Model model) throws Exception{
            model.addAttribute("id", id);
            return ResponseEntity.ok(memberService.selectMember(id));
        }

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
