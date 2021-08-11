package com.test.springtest.service;

import com.test.springtest.domain.Member;

import java.util.List;

public interface MemberService {

    List<Member> getMemberList();

    Member getMember(String id);

    boolean insertMember(String id, String name, String password);

    Member updateMember(String id, String name, String password);

    boolean deleteMember(String id);
}
