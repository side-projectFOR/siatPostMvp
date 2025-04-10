package com.siat.post.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

        @GetMapping("/test")
        public String test(@RequestParam("id") int id, Model model){
            model.addAttribute("id", id);
            System.out.println("Hello World");
            userService.insert(id);
            return "index.html";
        }

}
