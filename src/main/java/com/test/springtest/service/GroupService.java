package com.test.springtest.service;

import com.test.springtest.domain.Group;
import com.test.springtest.dto.GroupDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {
    Page<Group> getGroupList(Pageable pageable);

    Page<Group> getGroupList(Pageable pageable, GroupDTO groupDTO);

    Group getGroup(String id);

    boolean existGroup(String id);

    Group insertGroup(GroupDTO groupDTO);

    Group updateGroup(GroupDTO groupDTO);

    void deleteGroup(String id);
}
