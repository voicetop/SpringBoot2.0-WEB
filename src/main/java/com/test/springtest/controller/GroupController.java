package com.test.springtest.controller;

import com.test.springtest.domain.Group;
import com.test.springtest.dto.GroupDTO;
import com.test.springtest.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/Groups", produces = MediaTypes.HAL_JSON_VALUE)
@Api(tags = {"Group API"})
public class GroupController {

    @NonNull
    private GroupService groupService;

    @Operation(summary = "그룹 조회(Page)", description = "Group List Paged")
    @GetMapping("")
    public Page<Group> getGroupList(Pageable pageable, GroupDTO GroupDTO) throws InterruptedException {
        Page<Group> GroupList = groupService.getGroupList(pageable, GroupDTO);
        return GroupList;
    }

    @Operation(summary = "그룹 상세 조회", description = "Group Detail")
    @GetMapping("/{id}")
    public ResponseEntity getGroup(@PathVariable String id) throws InterruptedException {
        return ResponseEntity.ok(groupService.getGroup(id));
    }

    @Operation(summary = "그룹 생성", description = "그룹를 생성한다")
    @PostMapping("/")
    public ResponseEntity insertGroup(@Validated GroupDTO GroupDTO) throws InterruptedException {
        Group resultGroup = groupService.insertGroup(GroupDTO);
        if (resultGroup!=null) {
            return ResponseEntity.ok().body(
                        EntityModel.of(resultGroup).add(
                                linkTo(methodOn(GroupController.class).getGroup(resultGroup.getId())).withRel("query-event"),
                                linkTo(methodOn(GroupController.class).updateGroup(GroupDTO)).withRel("update-event"),
                                linkTo(methodOn(GroupController.class).deleteGroup(resultGroup.getId())).withRel("delete-event")
                        )
                    );
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "그룹 수정", description = "그룹 정보를 수정한다")
    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    public ResponseEntity updateGroup(@Validated GroupDTO GroupDTO) throws InterruptedException {
        groupService.updateGroup(GroupDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "그룹 삭제", description = "그룹를 삭제한다")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteGroup(@PathVariable String id) throws InterruptedException {
        groupService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }

}