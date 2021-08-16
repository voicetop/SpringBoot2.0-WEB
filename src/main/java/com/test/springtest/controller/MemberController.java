package com.test.springtest.controller;

import com.test.springtest.domain.Member;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.test.springtest.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/member", produces = MediaTypes.HAL_JSON_VALUE)
@Api(tags = {"Member API"})
public class MemberController {

    @NonNull
    private MemberService memberService;

    @Operation(summary = "사용자 조회(Page)", description = "Memeber List Paged")
    @GetMapping("/")
    public Page<Member> getMemberList(Pageable pageable) throws InterruptedException {
        Page<Member> memberList = memberService.getMemberList(pageable);
        return memberList;
    }

    @GetMapping("/{id}")
    public ResponseEntity getMember(@PathVariable String id) throws InterruptedException {
        return ResponseEntity.ok(memberService.getMember(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity insertMember(@PathVariable String id,
                                       @RequestBody Map<String, String> requestParam) throws InterruptedException {
        String name = (String) requestParam.get("name");
        String password = (String) requestParam.get("password");

        Member member = memberService.insertMember(id, name, password);
        if (member!=null) {
            return ResponseEntity.ok().body(
                        EntityModel.of(member).add(
                                linkTo(methodOn(MemberController.class).getMember(member.getId())).withRel("query-event"),
                                linkTo(methodOn(MemberController.class)).slash(member.getId()).withRel("update-event"),
                                linkTo(methodOn(MemberController.class).deleteMember(member.getId())).withRel("delete-event")
                        )
                    );
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    @PatchMapping("/{id}")
    public ResponseEntity updateMember(@PathVariable String id, @RequestParam Map<String, Object> requestParam) throws InterruptedException {
        String name = (String) requestParam.get("name");
        String password = (String) requestParam.get("password");

        memberService.updateMember(id, name, password);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable String id) throws InterruptedException {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }

}