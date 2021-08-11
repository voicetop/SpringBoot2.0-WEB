package com.test.springtest.service;

import com.test.springtest.domain.Member;
import com.test.springtest.reopsitory.MemberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @NonNull
    private final MemberRepository memberRepository;

    @Override
    public List<Member> getMemberList() {
        return memberRepository.getMemberList();
    }

    @Override
    public Member getMember(String id) {
        return memberRepository.getMemberById(id);
    }

    @Override
    public boolean insertMember(String id, String name, String password) {
        Member existMember = getMember(id);
        if (existMember != null) {
            return false;
        }

        Member member = Member.builder()
                .id(id)
                .name(name)
                .password(password)
                .build();
        return memberRepository.insertMember(member) > 0 ? true : false;
    }

    @Override
    public boolean updateMember(String id, String name, String password) {
        Member existMember = getMember(id);
        if (existMember == null) {
            return false;
        }

        Member member = Member.builder()
                .id(id)
                .name(name)
                .password(password)
                .build();
        return memberRepository.updateMember(member) > 0 ? true : false;
    }

    @Override
    public boolean deleteMember(String id) {
        Member existMember = getMember(id);
        if (existMember == null) {
            return false;
        }
        return memberRepository.deleteMemberById(id) > 0 ? true : false;
    }
}
