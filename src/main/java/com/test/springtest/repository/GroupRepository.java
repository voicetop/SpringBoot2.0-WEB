package com.test.springtest.repository;

import com.test.springtest.domain.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {

    Page<Group> findAllByNameLike(Pageable pageable, String name);

}