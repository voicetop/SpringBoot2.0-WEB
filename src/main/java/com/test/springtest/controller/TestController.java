package com.test.springtest.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.test.springtest.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class TestController {

    @NonNull
    private MemberService memberService;

    @GetMapping("/")
    public ResponseEntity getMemberList() throws InterruptedException {
        return ResponseEntity.ok(memberService.getMemberList());
    }

    @GetMapping("/{id}")
    public ResponseEntity getMember(@PathVariable String id) throws InterruptedException {
        return ResponseEntity.ok(memberService.getMember(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity insertMember(@PathVariable String id, @RequestParam Map<String, Object> requestParam) throws InterruptedException {
        String name = (String) requestParam.get("name");
        String password = (String) requestParam.get("password");

        if (memberService.insertMember(id, name, password)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMember(@PathVariable String id, @RequestParam Map<String, Object> requestParam) throws InterruptedException {
        String name = (String) requestParam.get("name");
        String password = (String) requestParam.get("password");

        if (memberService.updateMember(id, name, password)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable String id) throws InterruptedException {
        if (memberService.deleteMember(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

}