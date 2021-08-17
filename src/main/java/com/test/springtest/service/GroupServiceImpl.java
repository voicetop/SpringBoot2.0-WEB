package com.test.springtest.service;

import com.test.springtest.domain.Group;
import com.test.springtest.dto.GroupDTO;
import com.test.springtest.reopsitory.GroupRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service("groupService")
public class GroupServiceImpl implements GroupService {

    @NonNull
    private GroupRepository groupRepository;

    @Override
    public Page<Group> getGroupList(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @Override
    public Page<Group> getGroupList(Pageable pageable, GroupDTO groupDTO) {
        if(groupDTO.getName()!=null) {
            return groupRepository.findAllByNameLike(pageable, groupDTO.getName());
        }else {
            return this.getGroupList(pageable);
        }
    }

    @Override
    public Group getGroup(String id) {
        return groupRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existGroup(String id) {
        Group existGroup = groupRepository.findById(id).orElse(null);
        if (existGroup == null) {
            return false;
        }else{
            return true;
        }
    }

    @Transactional
    @Override
    public Group insertGroup(GroupDTO groupDTO) {
        if (this.existGroup(groupDTO.getId())) {
            return null;
        }
        return groupRepository.save(groupDTO.toEntity());
    }

    @Transactional
    @Override
    public Group updateGroup(GroupDTO groupDTO) {
        Group orgGroup = groupRepository.getById(groupDTO.getId());
        if (orgGroup == null) {
            return null;
        }
        return groupRepository.save(groupDTO.toEntity());
    }

    @Transactional
    @Override
    public void deleteGroup(String id) {
        groupRepository.deleteById(id);
    }
}
