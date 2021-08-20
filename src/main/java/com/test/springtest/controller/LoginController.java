package com.test.springtest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @GetMapping("")
    public String login() throws InterruptedException {
        return "login";
    }

    @GetMapping("/failure")
    public String loginFailure(Model model) throws InterruptedException {
        model.addAttribute("fail", true);
        return "login";
    }


}