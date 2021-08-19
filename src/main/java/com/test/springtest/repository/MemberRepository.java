package com.test.springtest.repository;

import com.test.springtest.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    Page<Member> findAllByNameLike(Pageable pageable, String name);

    Member findFirstById(String id);

    Member findFirstByIdAndName(String id, String name);


}