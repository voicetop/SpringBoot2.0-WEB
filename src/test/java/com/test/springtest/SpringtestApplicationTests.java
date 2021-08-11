package com.test.springtest;

import com.test.springtest.domain.Member;
import com.test.springtest.reopsitory.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SpringTestApplicationTests {

	@Autowired
	private MemberRepository memberRepository;

	@Test
	void contextLoads() {

		Member member = Member.builder()
				.id("test")
				.name("테스트")
				.password("test1234")
				.build();

		memberRepository.insertMember(member);

		member.setName("테스트123");
		Member updatedMember = memberRepository.updateMember(member);

		Assertions.assertEquals(updatedMember.getName(), "테스트123");

		Member retrivedMember = memberRepository.getMemberById(member.getId());

		Assertions.assertEquals(retrivedMember.getId(), "test");
		Assertions.assertEquals(retrivedMember.getName(), "테스트123");
		Assertions.assertEquals(retrivedMember.getPassword(), "test1234");

		log.debug("TEST DONE");
	}

}
