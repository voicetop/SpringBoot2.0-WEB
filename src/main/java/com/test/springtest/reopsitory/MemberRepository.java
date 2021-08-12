package com.test.springtest.reopsitory;

import com.test.springtest.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    public List<Member> findAllByNameLike(String name);

    public Member findByIdAndName(String id, String name);

}