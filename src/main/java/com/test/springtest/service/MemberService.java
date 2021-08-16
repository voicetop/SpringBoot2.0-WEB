package com.test.springtest.service;

import com.test.springtest.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface MemberService {

    Page<Member> getMemberList(Pageable pageable);

    Page<Member> getMemberList(Pageable pageable, String name);

    Member getMember(String id);

    Member getMember(String id, String name);

    Member insertMember(String id, String name, String password);

    boolean updateMember(String id, String name, String password);

    void deleteMember(String id);
}
