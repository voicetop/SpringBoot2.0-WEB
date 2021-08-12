package com.test.springtest;

import com.test.springtest.domain.Group;
import com.test.springtest.domain.Member;
import com.test.springtest.service.GroupService;
import com.test.springtest.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SpringTestApplicationTests {

	@Autowired
	private GroupService groupService;

	@Autowired
	private MemberService memberService;

	@Test
	void testGroupService() {
		log.debug("TEST GROUP START");

		String id = "test_group";
		String name = "테스트 그룹";

		//step1. insert
		groupService.insertGroup(id, name);

		//step2. update
		name = "테스트123_그룹";
		boolean updateResult = groupService.updateGroup(id, name);
		Assertions.assertEquals(updateResult, true);

		//step3. get
		Group retrivedGroup = groupService.getGroup(id);
		Assertions.assertEquals(retrivedGroup.getId(), id);
		Assertions.assertEquals(retrivedGroup.getName(), name);

		log.debug("TEST GROUP DONE");
	}

	@Test
	void testMemberService() {
		log.debug("TEST MEMBER START");

		String id = "test";
		String name = "테스트";
		String password = "test1234";

		//step1. insert
		memberService.insertMember(id, name, password);

		//step2. update
		name = "테스트123";
		boolean updateResult = memberService.updateMember(id, name, password);
		Assertions.assertEquals(updateResult, true);

		//step3. get
		Member retrivedMember = memberService.getMember(id);
		Assertions.assertEquals(retrivedMember.getId(), id);
		Assertions.assertEquals(retrivedMember.getName(), name);
		Assertions.assertEquals(retrivedMember.getPassword(), password);

		log.debug("TEST MEMBER DONE");
	}

}
