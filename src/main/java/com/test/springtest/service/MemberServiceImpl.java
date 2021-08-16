package com.test.springtest.service;

import com.test.springtest.domain.Member;
import com.test.springtest.reopsitory.MemberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @NonNull
    private MemberRepository memberRepository;

    @Override
    public Page<Member> getMemberList(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @Override
    public Page<Member> getMemberList(Pageable pageable, String name){
        return memberRepository.findAllByNameLike(pageable, name);
    }

    @Override
    public Member getMember(String id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member getMember(String id, String name) {
        return memberRepository.findByIdAndName(id, name);
    }

    @Transactional
    @Override
    public Member insertMember(String id, String name, String password) {
        Member member = Member.builder()
                .id(id)
                .name(name)
                .password(password)
                .build();

        return memberRepository.save(member);
    }

    @Transactional
    @Override
    public boolean updateMember(String id, String name, String password) {
        Member member = memberRepository.getById(id);
        if(member==null){
            return false;
        }
        member.setName(name);
        member.setPassword(password);
        return true;
    }

    @Transactional
    @Override
    public void deleteMember(String id) {
        memberRepository.deleteById(id);
    }
}
