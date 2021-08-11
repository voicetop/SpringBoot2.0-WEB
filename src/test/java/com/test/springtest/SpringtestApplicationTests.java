package com.test.springtest;

import com.test.springtest.domain.Member;
import com.test.springtest.reopsitory.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringtestApplicationTests {

	@Autowired
	private MemberRepository memberRepository;

	@Test
	void contextLoads() {

		Member member = Member.builder()
				.id("test")
				.name("테스트")
				.password("test1234")
				.build();

		int resCnt = memberRepository.insertMember(member);
		Assertions.assertEquals(resCnt, 1);

		Member retrivedMember = memberRepository.getMemberById(member.getId());

		Assertions.assertEquals(retrivedMember.getName(), "테스트");
		Assertions.assertEquals(retrivedMember.getPassword(), "test1234");
	}

}
