package com.test.springtest.reopsitory;

import com.test.springtest.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemberRepository {

    public List<Member> getMemberList();

    public Member getMemberById(String id);

    public void insertMember(Member member);

    public Member updateMember(Member member);

    public void deleteMember(Member member);

}