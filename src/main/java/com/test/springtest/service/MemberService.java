package com.test.springtest.service;

import com.test.springtest.domain.Member;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface MemberService {

    List<Member> getMemberList();

    List<Member> getMemberList(String name);

    Member getMember(String id);

    Member getMember(String id, String name);

    Member insertMember(String id, String name, String password);

    boolean updateMember(String id, String name, String password);

    void deleteMember(String id);
}
