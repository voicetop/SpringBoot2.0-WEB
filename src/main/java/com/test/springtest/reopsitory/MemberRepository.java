package com.test.springtest.reopsitory;

import com.test.springtest.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> getMemberList();

    Member getMemberById(String id);

    int insertMember(Member member);

    int updateMember(Member member);

    int deleteMemberById(String id);

}