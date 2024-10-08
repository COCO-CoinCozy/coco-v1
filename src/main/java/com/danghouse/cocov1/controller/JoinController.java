package com.danghouse.cocov1.controller;

import com.danghouse.cocov1.dto.JoinDTO;
import com.danghouse.cocov1.service.JoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class JoinController {

    private final JoinService joinService;

    @Autowired
    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("user", new JoinDTO());
        return "join";
    }

    @PostMapping("/joinProcess")
    public String joinProcess(@Validated @ModelAttribute("user") JoinDTO joinDTO, BindingResult bindingResult ) {

        // Username 중복 체크
        if (joinService.validateUserNameDuplication(joinDTO.getUsername()) && !joinDTO.getUsername().isEmpty()) {
            bindingResult.rejectValue("username", "", "❗이미 존재하는 사용자 이름입니다.");
        }

        // Email 중복 체크
        if (joinService.validateEmailDuplication(joinDTO.getEmail()) && !joinDTO.getEmail().isEmpty()) {
            bindingResult.rejectValue("email", "", "❗이미 존재하는 이메일입니다.");
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "/join";
        }

        joinService.joinProcess(joinDTO);

        return "redirect:/login";
    }
}
