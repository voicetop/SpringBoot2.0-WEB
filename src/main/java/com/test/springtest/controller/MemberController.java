package com.test.springtest.controller;

import com.test.springtest.domain.Member;
import com.test.springtest.dto.MemberDTO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.test.springtest.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/members", produces = MediaTypes.HAL_JSON_VALUE)
@Api(tags = {"Member API"})
public class MemberController {

    @NonNull
    private MemberService memberService;

    @Operation(summary = "사용자 조회(Page)", description = "Member List Paged")
    @GetMapping("")
    public Page<Member> getMemberList(Pageable pageable, MemberDTO memberDTO) throws InterruptedException {
        Page<Member> memberList = memberService.getMemberList(pageable, memberDTO);
        return memberList;
    }

    @Operation(summary = "사용자 상세 조회", description = "Member Detail")
    @GetMapping("/{id}")
    public ResponseEntity getMember(@PathVariable String id) throws InterruptedException {
        return ResponseEntity.ok(memberService.getMember(id));
    }

    @Operation(summary = "사용자 생성", description = "사용자를 생성한다")
    @PostMapping("/")
    public ResponseEntity insertMember(@Validated MemberDTO memberDTO) throws InterruptedException {
        Member resultMember = memberService.insertMember(memberDTO);
        if (resultMember!=null) {
            return ResponseEntity.ok().body(
                        EntityModel.of(resultMember).add(
                                linkTo(methodOn(MemberController.class).getMember(resultMember.getId())).withRel("query-event"),
                                linkTo(methodOn(MemberController.class).updateMember(memberDTO)).withRel("update-event"),
                                linkTo(methodOn(MemberController.class).deleteMember(resultMember.getId())).withRel("delete-event")
                        )
                    );
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "사용자 수정", description = "사용자 정보를 수정한다")
    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    public ResponseEntity updateMember(@Validated MemberDTO memberDTO) throws InterruptedException {
        memberService.updateMember(memberDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "사용자 삭제", description = "사용자를 삭제한다")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable String id) throws InterruptedException {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }

}