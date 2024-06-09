package com.danghouse.cocov1.controller;

import com.danghouse.cocov1.dto.JoinDTO;
import com.danghouse.cocov1.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private JoinService joinService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/loginProcess")
    public String loginProcess() {
        return "admin";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/joinProcess")
    public String joinProcess(JoinDTO joinDTO) {
        joinService.joinProcess(joinDTO);
        return "redirect:/login";
    }

}
