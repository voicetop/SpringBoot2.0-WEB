package com.test.springtest.service;

import com.test.springtest.domain.Group;

import java.util.List;

public interface GroupService {
    List<Group> getGroupList();

    List<Group> getGroupList(String  name);

    Group getGroup(String id);

    Group insertGroup(String id, String name);

    boolean updateGroup(String id, String name);

    void deleteGroup(String id);
}
