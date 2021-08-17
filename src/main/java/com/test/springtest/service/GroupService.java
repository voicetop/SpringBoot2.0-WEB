package com.test.springtest.service;

import com.test.springtest.domain.Group;
import com.test.springtest.dto.group.GroupDTO;
import com.test.springtest.dto.group.SearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {
    Page<Group> getGroupList(Pageable pageable);

    Page<Group> getGroupList(Pageable pageable, SearchDTO searchDTO);

    Group getGroup(String id);

    boolean existGroup(String id);

    Group insertGroup(GroupDTO groupDTO);

    Group updateGroup(GroupDTO groupDTO);

    void deleteGroup(String id);
}
