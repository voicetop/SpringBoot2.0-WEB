package com.test.springtest.service.impl;

import com.test.springtest.domain.Member;
import com.test.springtest.dto.member.MemberDTO;
import com.test.springtest.dto.member.SearchDTO;
import com.test.springtest.repository.MemberRepository;
import com.test.springtest.service.MemberService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Member> getMemberList(Pageable pageable, SearchDTO searchDTO) {
        if (searchDTO.getName() != null) {
            return memberRepository.findAllByNameLike(pageable, searchDTO.getName());
        } else {
            return this.getMemberList(pageable);
        }
    }

    @Override
    public Member getMember(String id) {
        return memberRepository.findById(id).orElseThrow();
    }

    @Override
    public Member getMember(String id, String name) {
        return memberRepository.findFirstByIdAndName(id, name);
    }

    @Override
    public boolean existMember(String id) {
        Member existMember = memberRepository.findFirstById(id);
        if (existMember == null) {
            return false;
        } else {
            return true;
        }
    }

    @Transactional
    @Override
    public Member insertMember(MemberDTO memberDTO) {
        if (this.existMember(memberDTO.getId())) {
            return null;
        }
        return memberRepository.save(memberDTO.toEntity());
    }

    @Transactional
    @Override
    public Member updateMember(MemberDTO memberDTO) {
        Member orgMember = memberRepository.getById(memberDTO.getId());
        if (orgMember == null) {
            return null;
        }
        return memberRepository.save(memberDTO.toEntity());
    }

    @Transactional
    @Override
    public void deleteMember(String id) {
        memberRepository.deleteById(id);
    }
}
