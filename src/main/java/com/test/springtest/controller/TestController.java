package com.test.springtest.controller;

import com.test.springtest.domain.Member;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.test.springtest.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class TestController {

    @NonNull
    private MemberService memberService;

    @GetMapping("/")
    public List<Member> getMemberList() throws InterruptedException {
        return memberService.getMemberList();
    }

    @GetMapping("/{id}")
    public Member getMember(@PathVariable String id) throws InterruptedException {
        return memberService.getMember(id);
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

        Member member = memberService.updateMember(id, name, password);
        if (member!=null) {
            return ResponseEntity.ok(member);
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