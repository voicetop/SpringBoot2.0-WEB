package com.test.springtest.reopsitory;

import com.test.springtest.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    public Page<Member> findAllByNameLike(Pageable pageable, String name);

    public Member findByIdAndName(String id, String name);

}