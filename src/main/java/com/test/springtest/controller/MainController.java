package com.test.springtest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/main")
public class MainController {

    @GetMapping("")
    public String main(Authentication authentication) throws InterruptedException {
        if(authentication==null){
            return "login";
        }

        Object principal = authentication.getPrincipal();
        if(DefaultOAuth2User.class.isInstance(principal)){
            DefaultOAuth2User user = (DefaultOAuth2User) principal;
            log.debug(user.toString());
        }

        return "main";
    }

}