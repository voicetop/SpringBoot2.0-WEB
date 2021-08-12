package com.test.springtest.service;

import com.test.springtest.domain.Group;
import com.test.springtest.reopsitory.GroupRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service("groupService")
public class GroupServiceImpl implements GroupService {

    @NonNull
    private GroupRepository groupRepository;

    @Override
    public List<Group> getGroupList() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> getGroupList(String name) {
        return groupRepository.findAllByNameLike(name);
    }

    @Override
    public Group getGroup(String id) {
        return groupRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Group insertGroup(String id, String name) {
        Group group = Group.builder()
                .id(id)
                .name(name)
                .build();

        return groupRepository.save(group);
    }

    @Transactional
    @Override
    public boolean updateGroup(String id, String name) {
        Group group = groupRepository.getById(id);
        if (group == null) {
            return false;
        }
        group.setName(name);
        return true;
    }

    @Transactional
    @Override
    public void deleteGroup(String id) {
        groupRepository.deleteById(id);
    }
}
