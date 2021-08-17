package com.test.springtest.service;

import com.test.springtest.domain.Member;
import com.test.springtest.dto.MemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    Page<Member> getMemberList(Pageable pageable);

    Page<Member> getMemberList(Pageable pageable, MemberDTO memberDTO);

    boolean existMember(String id);

    Member getMember(String id);

    Member getMember(String id, String name);

    Member insertMember(MemberDTO memberDTO);

    Member updateMember(MemberDTO memberDTO);

    void deleteMember(String id);
}
