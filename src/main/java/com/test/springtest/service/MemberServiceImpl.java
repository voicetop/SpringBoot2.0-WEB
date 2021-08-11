package com.test.springtest.service;

import com.test.springtest.domain.Member;
import com.test.springtest.reopsitory.MemberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @NonNull
    private MemberRepository memberRepository;

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

        Member newMember = Member.builder()
                .id(id)
                .name(name)
                .password(password)
                .build();

        memberRepository.insertMember(newMember);
        return  true;
    }

    @Override
    public Member updateMember(String id, String name, String password) {
        Member existMember = getMember(id);
        if (existMember == null) {
            return null;
        }
        existMember.setName(name);
        existMember.setPassword(password);

        return memberRepository.updateMember(existMember);
    }

    @Override
    public boolean deleteMember(String id) {
        Member existMember = getMember(id);
        if (existMember == null) {
            return false;
        }
        memberRepository.deleteMember(existMember);
        return true;
    }
}
