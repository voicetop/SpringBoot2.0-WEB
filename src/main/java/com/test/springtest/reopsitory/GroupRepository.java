package com.test.springtest.reopsitory;

import com.test.springtest.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {

    public List<Group> findAllByNameLike(String name);

}